package protocol.engine.village

import java.net.URL
import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import licos.entity.{HostPlayer, VillageInfoFromLobby}
import licos.json2protocol.village.Json2VillageMessageProtocol
import licos.knowledge.{Composition, HumanArchitecture, HumanPlayerLobby, RandomAvatarSetting}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.engine.processing.village.{VillageProcessingEngine, VillageProcessingEngineFactory}
import licos.protocol.engine.processing.{SpecificProcessingEngineFactory, VillagePE}
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import play.api.libs.json.Json
import protocol.element.VillageMessageTestProtocol
import protocol.engine.VillageExample
import protocol.engine.village.analysis.client2server.{
  AnonymousAudienceChatFromClientAE,
  BoardAE,
  BuildVillageAE,
  ChatFromClientAE,
  ErrorFromClientAE,
  LeaveWaitingPageAE,
  OnymousAudienceBoardAE,
  OnymousAudienceChatFromClientAE,
  OnymousAudienceScrollAE,
  ReadyAE,
  ReceivedChatMessageAE,
  ReceivedFlavorTextMessageAE,
  ReceivedSystemMessageAE,
  ScrollAE,
  StarAE,
  VoteAE
}
import protocol.engine.village.analysis.server2client.{
  AnonymousAudienceChatFromServerAE,
  ChatFromServerAE,
  ErrorFromServerAE,
  FirstMorningPhaseAE,
  FlavorTextAE,
  GameResultAE,
  MorningPhaseAE,
  NextGameInvitationAE,
  NextGameInvitationIsClosedAE,
  NightPhaseAE,
  NoonPhaseAE,
  OnymousAudienceChatFromServerAE,
  PostMortemDiscussionAE
}
import protocol.engine.village.example.client2server.{
  AnonymousAudienceChatFromClient,
  Board,
  ChatFromClient,
  ErrorFromClient,
  OnymousAudienceBoard,
  OnymousAudienceChatFromClient,
  OnymousAudienceScroll,
  ReceivedChatMessage,
  ReceivedFlavorTextMessage,
  ReceivedSystemMessage,
  Scroll,
  Star,
  Vote
}
import protocol.engine.village.example.server2client.{
  AnonymousAudienceChatFromServer,
  ChatFromServer,
  ErrorFromServer,
  FirstMorningPhase,
  FlavorText,
  GameResult,
  MorningPhase,
  NextGameInvitation,
  NextGameInvitationIsClosed,
  NightPhase,
  NoonPhase,
  OnymousAudienceChatFromServer,
  PostMortemDiscussion
}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success}

object VillageProcessingEngineSuite {
  @DataPoints
  def exampleSeq: Array[VillageExample] = Array[VillageExample](
    ReceivedChatMessage("receipt/receivedChatMessage.json"),
    ReceivedFlavorTextMessage("receipt/receivedFlavorTextMessage.json"),
    ReceivedSystemMessage("receipt/receivedSystemMessage.json"),
    AnonymousAudienceChatFromClient("anonymousAudienceChat.jsonld"),
    Board("board.jsonld"),
    ChatFromClient("chat.jsonld"),
    ErrorFromClient("error.jsonld"),
    Vote("nightVote.jsonld"),
    Vote("noonVote.jsonld"),
    OnymousAudienceBoard("onymousAudienceBoard.jsonld"),
    OnymousAudienceChatFromClient("onymousAudienceChat.jsonld"),
    OnymousAudienceScroll("onymousAudienceScroll.jsonld"),
    Scroll("scroll.jsonld"),
    Star("star.jsonld"),
    NextGameInvitation("invitation/nextGameInvitation.json"),
    NextGameInvitationIsClosed("invitation/nextGameInvitationIsClosed.json"),
    AnonymousAudienceChatFromServer("anonymousAudienceChat.jsonld"),
    ErrorFromServer("error.jsonld"),
    FirstMorningPhase("firstMorning.jsonld"),
    FlavorText("flavorText.jsonld"),
    MorningPhase("morning.jsonld"),
    ChatFromServer("myMessageOnChat.jsonld"),
    NightPhase("night.jsonld"),
    NoonPhase("noon.jsonld"),
    OnymousAudienceChatFromServer("onymousAudienceChat.jsonld"),
    PostMortemDiscussion("postMortemDiscussion.jsonld"),
    GameResult("result.jsonld"),
    ChatFromServer("theirMessageOnChat.jsonld")
  )
}

@RunWith(classOf[Theories])
final class VillageProcessingEngineSuite extends AssertionsForJUnit {

  private val log: Logger = Logger[VillageProcessingEngineSuite]

  private val processingEngineFactory: VillageProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(VillagePE)
    .asInstanceOf[VillageProcessingEngineFactory]
    .set(new AnonymousAudienceChatFromClientAE())
    .set(new BoardAE())
    .set(new BuildVillageAE())
    .set(new ChatFromClientAE())
    .set(new ErrorFromClientAE())
    .set(new LeaveWaitingPageAE())
    .set(new OnymousAudienceBoardAE())
    .set(new OnymousAudienceChatFromClientAE())
    .set(new OnymousAudienceScrollAE())
    .set(new ReadyAE())
    .set(new ReceivedChatMessageAE())
    .set(new ReceivedFlavorTextMessageAE())
    .set(new ReceivedSystemMessageAE())
    .set(new ScrollAE())
    .set(new StarAE())
    .set(new VoteAE())
    .set(new NextGameInvitationAE())
    .set(new NextGameInvitationIsClosedAE())
    .set(new AnonymousAudienceChatFromServerAE())
    .set(new ChatFromServerAE())
    .set(new ErrorFromServerAE())
    .set(new FirstMorningPhaseAE())
    .set(new FlavorTextAE())
    .set(new GameResultAE())
    .set(new MorningPhaseAE())
    .set(new NightPhaseAE())
    .set(new NoonPhaseAE())
    .set(new OnymousAudienceChatFromServerAE())
    .set(new PostMortemDiscussionAE())

  private val processingEngine: VillageProcessingEngine = processingEngineFactory.create

  @Theory
  def process(jsonExample: VillageExample): Unit = {

    val hostPlayer = HostPlayer(
      1L,
      "Anonymous",
      isAnonymous = true,
      HumanArchitecture
    )
    val villageInfoFromLobby = VillageInfoFromLobby(
      HumanPlayerLobby,
      hostPlayer,
      Composition.support.`for`(15).A,
      1,
      RandomAvatarSetting,
      15,
      None,
      "Christopher",
      new URL("https://werewolf.world/image/0.3/character_icons/50x50/a_50x50.png")
    )

    val box = new VillageBox(villageInfoFromLobby)

    val jsonType:       String = jsonExample.`type`
    val url:            URL    = jsonExample.path
    implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
    log.info(url.toString)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    log.debug(msg)
    Json2VillageMessageProtocol.toProtocolOpt(Json.parse(msg), villageInfoFromLobby) match {
      case Some(protocol: VillageMessageProtocol) =>
        processingEngine.process(box, protocol) match {
          case Success(protocol: VillageMessageProtocol) =>
            protocol match {
              case p: VillageMessageTestProtocol =>
                assert(p.text == jsonType)
              case _ =>
                fail("No VillageMessageTestProtocol")
            }
          case Failure(error: Throwable) =>
            fail(
              Seq[String](
                "No response is generated.",
                error.getMessage,
                msg
              ).mkString("\n")
            )
        }
      case _ =>
        fail("No protocol")
    }
  }
}
