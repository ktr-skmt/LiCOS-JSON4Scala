package engine.village

import java.nio.charset.StandardCharsets

import engine.Example
import engine.village.analysis.{BoardAE, BuildVillageAE, ChatFromClientAE, ChatFromServerAE, ErrorAE, FlavorTextAE, GameResultAE, LeaveWaitingPageAE, PhaseAE, ReadyAE, ReceivedFlavorTextMessageAE, ReceivedPlayerMessageAE, ReceivedSystemMessageAE, ScrollAE, VoteAE}
import engine.village.example.{Board, ChatFromClient, ChatFromServer, Error, FlavorText, Phase, ReceivedFlavorTextMessage, ReceivedPlayerMessage, ReceivedSystemMessage, Scroll, Vote}
import entity.JsonTest
import licos.json.engine.processing.VillageProcessingEngine
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import play.api.libs.json.{JsResult, JsValue}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success, Try}

object VillageProcessingEngineSpec {

  @DataPoints
  def jsonExampleSeq: Array[Example] = Array[Example](
    ReceivedFlavorTextMessage("client2server/receipt/receivedFlavorTextMessage.json"),
    ReceivedPlayerMessage("client2server/receipt/receivedPlayerMessage.json"),
    ReceivedSystemMessage("client2server/receipt/receivedSystemMessage.json"),
    //ChatFromClient("client2server/anonymous-audience-chat.jsonld"),
    Board("client2server/board.jsonld"),
    ChatFromClient("client2server/chat.jsonld"),
    Vote("client2server/day-vote.jsonld"),
    Error("client2server/error.jsonld"),
    Vote("client2server/night-vote.jsonld"),
    //Board("client2server/onymous-audience-board.jsonld"),
    //ChatFromClient("client2server/onymous-audience-chat.jsonld"),
    Scroll("client2server/onymous-audience-scroll.jsonld"),
    Scroll("client2server/scroll.jsonld"),
    //Star("client2server/star.jsonld"),
    //NextGameInvitationIsClosed("server2client/invitation/next-game-invitation-is-closed.json"),
    //NextGameInvitation("server2client/invitation/next-game-invitation.json"),
    //ChatFromServer("server2client/anonymous-audience-chat.jsonld"),
    Phase("server2client/day.jsonld"),
    Error("server2client/error.jsonld"),
    Phase("server2client/first-morning.jsonld"),
    FlavorText("server2client/flavor-text.jsonld"),
    Phase("server2client/morning.jsonld"),
    ChatFromServer("server2client/my-message-on-chat.jsonld"),
    Phase("server2client/night.jsonld"),
    //ChatFromServer("server2client/onymous-audience-chat.jsonld"),
    Phase("server2client/post-mortem.jsonld"),
    //Phase("server2client/result.jsonld"),
    ChatFromServer("server2client/their-message-on-chat.jsonld")
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
    new PhaseAE(),
    new FlavorTextAE(),
    new GameResultAE(),
    new BuildVillageAE(),
    new LeaveWaitingPageAE(),
    new ErrorAE()
  )

  @Theory
  def process(jsonExample: Example): Unit = {
    val jsonType: String = jsonExample.`type`
    val url: String = jsonExample.path
    implicit val codec: Codec = Codec(StandardCharsets.UTF_8)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    processingEngine.process(new Box(jsonType), msg) match {
      case Some(jsValue: JsValue) =>
        parseJsonTest(jsValue) match {
          case Some(json: JsonTest) =>
            assert(json.text == jsonType)
          case None => fail(s"Something is wrong right after parsing.\n$msg")
        }

      case None => fail(s"No response is generated.\n$msg")
    }
  }

  private def parseJsonTest(jsValue: JsValue): Option[JsonTest] = {
    Try(jsValue.validate[JsonTest]) match {
      case Success(json: JsResult[JsonTest]) => json.asOpt
      case Failure(err: Throwable) =>
        fail(s"Parsing failed.\n${err.getMessage}\n${jsValue.toString}")
        None
    }
  }
}
