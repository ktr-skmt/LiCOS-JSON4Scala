package protocol.engine.village

import java.nio.charset.StandardCharsets
import java.util.Locale

import com.typesafe.scalalogging.Logger
import licos.entity.{AvatarInVillage, HostPlayer, Village, VillageFactory}
import licos.json.parser.VillageParser
import licos.knowledge.{Cast, HumanArchitecture, HumanPlayerLobby, RandomAvatarSetting}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.engine.processing.{
  SpecificProcessingEngineFactory,
  VillagePE,
  VillageProcessingEngine,
  VillageProcessingEngineFactory
}
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
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
import protocol.engine.village.example.client2server.{
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

import scala.collection.mutable.ListBuffer
import scala.io.{Codec, Source}
import scala.util.{Failure, Success}

object VillageProcessingEngineSpec {
  @DataPoints
  def exampleSeq: Array[VillageExample] = Array[VillageExample](
    ReceivedChatMessage("receipt/receivedChatMessage.json"),
    ReceivedFlavorTextMessage("receipt/receivedFlavorTestMessage.json"),
    ReceivedSystemMessage("receipt/receivedSystemMessage.json"),
    ChatFromClient("anonymousAudienceChat.jsonld"),
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
class VillageProcessingEngineSpec extends AssertionsForJUnit with VillageParser {

  private final val log: Logger = Logger[VillageProcessingEngineSpec]

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

  private val processingEngine: VillageProcessingEngine = processingEngineFactory.create

  @Theory
  def process(jsonExample: VillageExample): Unit = {

    val hostPlayer = HostPlayer(
      1L,
      "Christopher",
      isAnonymous = true,
      HumanArchitecture
    )
    val avatars = ListBuffer.empty[AvatarInVillage]
    val cast: Cast = Cast.playerNumRoleNumMap(15)("A")
    val villageFactory = new VillageFactory()
      .setId(1L)
      .setName("Fearwick")
      .setLobby(HumanPlayerLobby)
      .setHostPlayer(hostPlayer)
      .setAvatarSetting(RandomAvatarSetting)
      .setMaxNumberOfHumanPlayers(15)
      .setMaxNumberOfChatMessages(10)
      .setMaxLengthOfUnicodeCodePoints(140)
      .setLanguage(Locale.JAPANESE)
      .setStory(1)
      .setIdForSearching(1)
      .setCast(cast)
      .setAvatars(avatars)

    villageFactory.create match {
      case Success(village: Village) =>
        val box = new VillageBox(village)

        val jsonType:       String = jsonExample.`type`
        val url:            String = jsonExample.path
        implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
        log.info(url)
        val source = Source.fromURL(url)
        val msg: String = source.getLines.mkString("\n")
        source.close()
        log.debug(msg)
        processingEngine.process(box, msg) match {
          case Success(protocol: VillageMessageProtocol) =>
            protocol match {
              case p: VillageMessageTestProtocol =>
                assert(p.text == jsonType)
              case _ =>
                fail(
                  Seq[String](
                    "No AuthMessageTestProtocol"
                  ).mkString("\n")
                )
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
    }
  }
}
