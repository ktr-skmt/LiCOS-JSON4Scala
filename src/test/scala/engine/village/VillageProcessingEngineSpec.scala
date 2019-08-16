package engine.village

import java.nio.charset.StandardCharsets

import engine.VillageExample
import engine.village.analysis.client2server._
import engine.village.analysis.server2client._
import engine.village.example.client2server._
import engine.village.example.server2client._
import element.JsonTest
import licos.json.engine.processing.{SpecificProcessingEngineFactory, VillagePE, VillageProcessingEngine, VillageProcessingEngineFactory}
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
    example.client2server.AudienceChat("anonymous-audience-chat.jsonld"),
    Board("board.jsonld"),
    example.client2server.Chat("chat.jsonld"),
    Vote("day-vote.jsonld"),
    //example.client2server.Error(client2server("error.jsonld")),
    Vote("night-vote.jsonld"),
    //Board("onymous-audience-board.jsonld"),
    //example.client2server.AudienceChat("onymous-audience-chat.jsonld"),
    Scroll("onymous-audience-scroll.jsonld"),
    Scroll("scroll.jsonld"),
    Star("star.jsonld"),
    NextGameInvitationIsClosed(invitation("next-game-invitation-is-closed.json")),
    NextGameInvitation(invitation("next-game-invitation.json")),
    //example.server2client.AudienceChat("anonymous-audience-chat.jsonld"),
    Phase("day.jsonld"),
    //example.server2client.Error(server2client("error.jsonld")),
    Phase("first-morning.jsonld"),
    FlavorText("flavor-text.jsonld"),
    Phase("morning.jsonld"),
    example.server2client.Chat("my-message-on-chat.jsonld"),
    Phase("night.jsonld"),
    //example.server2client.AudienceChat("onymous-audience-chat.jsonld"),
    Phase("post-mortem.jsonld"),
    //GameResult("result.jsonld"),
    example.server2client.Chat("their-message-on-chat.jsonld")
  )
}

@RunWith(classOf[Theories])
class VillageProcessingEngineSpec extends AssertionsForJUnit {

  private val processingEngineFactory: VillageProcessingEngineFactory = SpecificProcessingEngineFactory.
    create(VillagePE).
    asInstanceOf[VillageProcessingEngineFactory].
    set(new ReadyAE()).
    set(new ReceivedPlayerMessageAE()).
    set(new ReceivedSystemMessageAE()).
    set(new ReceivedFlavorTextMessageAE()).
    set(new ChatFromClientAE()).
    set(new ChatFromServerAE()).
    set(new AudienceChatFromClientAE()).
    set(new AudienceChatFromServerAE()).
    set(new BoardAE()).
    set(new VoteAE()).
    set(new ScrollAE()).
    set(new StarAE()).
    set(new PhaseAE()).
    set(new FlavorTextAE()).
    set(new GameResultAE()).
    set(new BuildVillageAE()).
    set(new LeaveWaitingPageAE()).
    set(new NextGameInvitationAE()).
    set(new NextGameInvitationIsClosedAE()).
    set(new ErrorFromClientAE()).
    set(new ErrorFromServerAE())

  private val processingEngine: VillageProcessingEngine = processingEngineFactory.create

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