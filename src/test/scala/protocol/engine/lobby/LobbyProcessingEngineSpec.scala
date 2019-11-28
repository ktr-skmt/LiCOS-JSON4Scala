package protocol.engine.lobby

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import licos.json.parser.LobbyParser
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.engine.processing.{LobbyPE, LobbyProcessingEngine, LobbyProcessingEngineFactory, SpecificProcessingEngineFactory}
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.LobbyExample
import protocol.engine.lobby.example.{AdvancedSearch, AvatarInfo, BuildVillage, ChangeLang, ChangeUserEmail, ChangeUserName, ChangeUserPassword, EnterLobby, GetAvatarInfo, GetSettings, IdSearch, KickOutPlayer, LeaveWaitingPage, Lobby, Ping, Play, Played, PlayedWithToken, Pong, Ready, SearchResult, SelectVillage, Settings, WaitingPage}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success}

object LobbyProcessingEngineSpec {
  @DataPoints
  def exampleSeq: Array[LobbyExample] = Array[LobbyExample](
    AdvancedSearch("advancedSearch.json"),
    AvatarInfo("avatar.json"),
    BuildVillage("buildVillage.json"),
    ChangeLang("changeLang.json"),
    ChangeUserEmail("changeUserEmail.json"),
    ChangeUserName("changeUserName.json"),
    ChangeUserPassword("changeUserPassword.json"),
    EnterLobby("enterLobby.json"),
    GetAvatarInfo("getAvatar.json"),
    GetSettings("getSettings.json"),
    IdSearch("idSearch.json"),
    KickOutPlayer("kickOutPlayer.json"),
    LeaveWaitingPage("leaveWaitingPage.json"),
    Lobby("lobby.json"),
    Ping("ping.json"),
    Play("play.json"),
    Played("played.json"),
    PlayedWithToken("playedWithToken.json"),
    Pong("pong.json"),
    Ready("ready.json"),
    SearchResult("searchResult.json"),
    SelectVillage("selectVillage.json"),
    Settings("settings.json"),
    WaitingPage("waitingPage.json")
  )
}

@RunWith(classOf[Theories])
class LobbyProcessingEngineSpec extends AssertionsForJUnit with LobbyParser {

  private final val log: Logger = Logger[LobbyProcessingEngineSpec]

  private val processingEngineFactory: LobbyProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(LobbyPE)
    .asInstanceOf[LobbyProcessingEngineFactory]

  private val processingEngine: LobbyProcessingEngine = processingEngineFactory.create

  @Theory
  def process(jsonExample: LobbyExample): Unit = {
    val jsonType:       String = jsonExample.`type`
    val url:            String = jsonExample.path
    implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
    log.info(url)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    log.debug(msg)
    processingEngine.process(new LobbyBox(), msg) match {
      case Success(protocol: LobbyMessageProtocol) =>
        protocol match {
          case p: LobbyMessageTestProtocol =>
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
