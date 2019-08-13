package engine.village

import java.nio.charset.StandardCharsets

import engine.VillageExample
import engine.village.analysis.{BoardAE, BuildVillageAE, ChatFromClientAE, ChatFromServerAE, ErrorAE, FlavorTextAE, GameResultAE, LeaveWaitingPageAE, NextGameInvitationAE, NextGameInvitationIsClosedAE, PhaseAE, ReadyAE, ReceivedFlavorTextMessageAE, ReceivedPlayerMessageAE, ReceivedSystemMessageAE, ScrollAE, StarAE, VoteAE}
import engine.village.example.{Board, ChatFromClient, ChatFromServer, Error, FlavorText, GameResult, NextGameInvitation, NextGameInvitationIsClosed, Phase, ReceivedFlavorTextMessage, ReceivedPlayerMessage, ReceivedSystemMessage, Scroll, Star, Vote}
import entity.JsonTest
import licos.json.engine.processing.VillageProcessingEngine
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import play.api.libs.json.{JsResult, JsValue}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success, Try}

object VillageProcessingEngineSpec {

  import engine.ClientToServerVillageExample.client2server
  import engine.ServerToClientVillageExample.server2client
  import engine.ReceiptVillageExample.receipt
  import engine.InvitationVillageExample.invitation

  @DataPoints
  def jsonExampleSeq: Array[VillageExample] = Array[VillageExample](
    ReceivedFlavorTextMessage(receipt("received-flavor-text-message.json")),
    ReceivedPlayerMessage(receipt("received-player-message.json")),
    ReceivedSystemMessage(receipt("received-system-message.json")),
    //ChatFromClient("anonymous-audience-chat.jsonld"),
    Board("board.jsonld"),
    ChatFromClient("chat.jsonld"),
    Vote("day-vote.jsonld"),
    Error(client2server("error.jsonld")),
    Vote("night-vote.jsonld"),
    //Board("onymous-audience-board.jsonld"),
    //ChatFromClient("onymous-audience-chat.jsonld"),
    Scroll("onymous-audience-scroll.jsonld"),
    Scroll("scroll.jsonld"),
    Star("star.jsonld"),
    NextGameInvitationIsClosed(invitation("next-game-invitation-is-closed.json")),
    NextGameInvitation(invitation("next-game-invitation.json")),
    //ChatFromServer("anonymous-audience-chat.jsonld"),
    Phase("day.jsonld"),
    Error(server2client("error.jsonld")),
    Phase("first-morning.jsonld"),
    FlavorText("flavor-text.jsonld"),
    Phase("morning.jsonld"),
    ChatFromServer("my-message-on-chat.jsonld"),
    Phase("night.jsonld"),
    //ChatFromServer("onymous-audience-chat.jsonld"),
    Phase("post-mortem.jsonld"),
    //GameResult("result.jsonld"),
    ChatFromServer("their-message-on-chat.jsonld")
  )
}

@RunWith(classOf[Theories])
class VillageProcessingEngineSpec extends AssertionsForJUnit {
  private val processingEngine = new VillageProcessingEngine(
    new ReadyAE(),
    new ReceivedPlayerMessageAE(),
    new ReceivedSystemMessageAE(),
    new ReceivedFlavorTextMessageAE(),
    new ChatFromClientAE(),
    new ChatFromServerAE(),
    new BoardAE(),
    new VoteAE(),
    new ScrollAE(),
    new StarAE(),
    new PhaseAE(),
    new FlavorTextAE(),
    new GameResultAE(),
    new BuildVillageAE(),
    new LeaveWaitingPageAE(),
    new NextGameInvitationAE(),
    new NextGameInvitationIsClosedAE(),
    new ErrorAE()
  )

  @Theory
  def process(jsonExample: VillageExample): Unit = {
    val jsonType: String = jsonExample.`type`
    val url: String = jsonExample.path
    implicit val codec: Codec = Codec(StandardCharsets.UTF_8)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    processingEngine.process(new VillageBox(jsonType), msg) match {
      case Some(jsValue: JsValue) =>
        parseJsonTest(jsValue) match {
          case Some(json: JsonTest) =>
            assert(json.text == jsonType)
          case None => fail(Seq[String](
            "Something is wrong right after parsing.",
            msg
          ).mkString("\n"))
        }

      case None => fail(Seq[String](
        "No response is generated.",
        msg
      ).mkString("\n"))
    }
  }

  private def parseJsonTest(jsValue: JsValue): Option[JsonTest] = {
    Try(jsValue.validate[JsonTest]) match {
      case Success(json: JsResult[JsonTest]) => json.asOpt
      case Failure(err: Throwable) =>
        fail(Seq[String](
          "Parsing failed.",
          err.getMessage,
          jsValue.toString
        ).mkString("\n"))
        None
    }
  }
}