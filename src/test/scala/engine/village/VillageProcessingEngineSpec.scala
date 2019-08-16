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
    ReceivedFlavorTextMessage(receipt("receivedFlavorTextMessage.json")),
    ReceivedPlayerMessage(receipt("receivedPlayerMessage.json")),
    ReceivedSystemMessage(receipt("receivedSystemMessage.json")),
    example.client2server.AudienceChat("anonymousAudienceChat.jsonld"),
    Board("board.jsonld"),
    example.client2server.Chat("chat.jsonld"),
    Vote("dayVote.jsonld"),
    //example.client2server.Error(client2server("error.jsonld")),
    Vote("nightVote.jsonld"),
    //Board("onymousAudienceBoard.jsonld"),
    //example.client2server.AudienceChat("onymousAudienceChat.jsonld"),
    Scroll("onymousAudienceScroll.jsonld"),
    Scroll("scroll.jsonld"),
    Star("star.jsonld"),
    NextGameInvitationIsClosed(invitation("nextGameInvitationIsClosed.json")),
    NextGameInvitation(invitation("nextGameInvitation.json")),
    //example.server2client.AudienceChat("anonymousAudienceChat.jsonld"),
    Phase("day.jsonld"),
    //example.server2client.Error(server2client("error.jsonld")),
    Phase("firstMorning.jsonld"),
    FlavorText("flavorText.jsonld"),
    Phase("morning.jsonld"),
    example.server2client.Chat("myMessageOnChat.jsonld"),
    Phase("night.jsonld"),
    //example.server2client.AudienceChat("onymousAudienceChat.jsonld"),
    Phase("postMortem.jsonld"),
    //GameResult("result.jsonld"),
    example.server2client.Chat("theirMessageOnChat.jsonld")
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