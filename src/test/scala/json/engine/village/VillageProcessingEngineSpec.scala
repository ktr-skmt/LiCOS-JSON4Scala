package json.engine.village

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import json.engine.VillageExample
import json.engine.village.analysis.client2server._
import json.engine.village.analysis.server2client._
import json.engine.village.example.client2server._
import json.engine.village.example.server2client._
import json.element.JsonTest
import licos.json.engine.processing.{
  SpecificProcessingEngineFactory,
  VillagePE,
  VillageProcessingEngine,
  VillageProcessingEngineFactory
}
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
    ReceivedFlavorTextMessage(receipt("receivedFlavorTextMessage.json")),
    ReceivedChatMessage(receipt("receivedChatMessage.json")),
    ReceivedSystemMessage(receipt("receivedSystemMessage.json")),
    example.client2server.AnonymousAudienceChat("anonymousAudienceChat.jsonld"),
    example.client2server.OnymousAudienceChat("onymousAudienceChat.jsonld"),
    Board("board.jsonld"),
    example.client2server.Chat("chat.jsonld"),
    Vote("noonVote.jsonld"),
    example.client2server.Error(client2server("error.jsonld")),
    Vote("nightVote.jsonld"),
    OnymousAudienceBoard("onymousAudienceBoard.jsonld"),
    OnymousAudienceScroll("onymousAudienceScroll.jsonld"),
    Scroll("scroll.jsonld"),
    Star("star.jsonld"),
    NextGameInvitationIsClosed(invitation("nextGameInvitationIsClosed.json")),
    NextGameInvitation(invitation("nextGameInvitation.json")),
    example.server2client.AnonymousAudienceChat("anonymousAudienceChat.jsonld"),
    Phase("noon.jsonld"),
    example.server2client.Error(server2client("error.jsonld")),
    Phase("firstMorning.jsonld"),
    FlavorText("flavorText.jsonld"),
    Phase("morning.jsonld"),
    example.server2client.Chat("myMessageOnChat.jsonld"),
    Phase("night.jsonld"),
    example.server2client.OnymousAudienceChat("onymousAudienceChat.jsonld"),
    Phase("postMortemDiscussion.jsonld"),
    GameResult("result.jsonld"),
    example.server2client.Chat("theirMessageOnChat.jsonld")
  )
}

@RunWith(classOf[Theories])
class VillageProcessingEngineSpec extends AssertionsForJUnit {

  private final val log: Logger = Logger[VillageProcessingEngineSpec]

  private val processingEngineFactory: VillageProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(VillagePE)
    .asInstanceOf[VillageProcessingEngineFactory]
    .set(new ReadyAE())
    .set(new ReceivedChatMessageAE())
    .set(new ReceivedSystemMessageAE())
    .set(new ReceivedFlavorTextMessageAE())
    .set(new ChatFromClientAE())
    .set(new ChatFromServerAE())
    .set(new OnymousAudienceChatFromClientAE())
    .set(new AnonymousAudienceChatFromClientAE())
    .set(new OnymousAudienceChatFromServerAE())
    .set(new AnonymousAudienceChatFromServerAE())
    .set(new BoardAE())
    .set(new OnymousAudienceBoardAE())
    .set(new VoteAE())
    .set(new ScrollAE())
    .set(new OnymousAudienceScrollAE())
    .set(new StarAE())
    .set(new PhaseAE())
    .set(new FlavorTextAE())
    .set(new GameResultAE())
    .set(new BuildVillageAE())
    .set(new LeaveWaitingPageAE())
    .set(new NextGameInvitationAE())
    .set(new NextGameInvitationIsClosedAE())
    .set(new ErrorFromClientAE())
    .set(new ErrorFromServerAE())

  private val processingEngine: VillageProcessingEngine = processingEngineFactory.create

  @Theory
  def process(jsonExample: VillageExample): Unit = {
    val jsonType:       String = jsonExample.`type`
    val url:            String = jsonExample.path
    implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
    log.info(url)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    log.debug(msg)
    processingEngine.process(new VillageBox(jsonType), msg) match {
      case Right(jsValue: JsValue) =>
        parseJsonTest(jsValue) match {
          case Some(json: JsonTest) =>
            assert(json.text == jsonType)
          case None =>
            fail(
              Seq[String](
                "Something is wrong right after parsing.",
                msg
              ).mkString("\n")
            )
        }

      case Left(jsValue: JsValue) =>
        fail(
          Seq[String](
            "No response is generated.",
            msg,
            jsValue.toString
          ).mkString("\n")
        )
    }
  }

  private def parseJsonTest(jsValue: JsValue): Option[JsonTest] = {
    Try(jsValue.validate[JsonTest]) match {
      case Success(json: JsResult[JsonTest]) => json.asOpt
      case Failure(err:  Throwable) =>
        fail(
          Seq[String](
            "Parsing failed.",
            err.getMessage,
            jsValue.toString
          ).mkString("\n")
        )
        None
    }
  }
}
