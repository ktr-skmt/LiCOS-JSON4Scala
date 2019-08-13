package engine.lobby

import java.nio.charset.StandardCharsets

import engine.LobbyExample
import engine.lobby.analysis.{AdvancedSearchAE, AvatarInfoAE, BuildVillageAE, ChangeLangAE, ChangeUserEmailAE, ChangeUserNameAE, ChangeUserPasswordAE, EnterLobbyAE, GetAvatarInfoAE, GetSettingsAE, IdSearchAE, KickOutPlayerAE, LeaveWaitingPageAE, LobbyAE, PingAE, PlayAE, PlayedWithTokenAE, PongAE, ReadyAE, SearchResultAE, SelectVillageAE, SettingsAE, WaitingPageAE}
import engine.lobby.example.{AdvancedSearch, AvatarInfo, BuildVillage, ChangeLang, ChangeUserEmail, ChangeUserName, ChangeUserPassword, EnterLobby, GetAvatarInfo, GetSettings, IdSearch, KickOutPlayer, LeaveWaitingPage, Lobby, Ping, Play, PlayedWithToken, Pong, Ready, SearchResult, SelectVillage, Settings, WaitingPage}
import entity.JsonTest
import licos.json.engine.processing.LobbyProcessingEngine
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import play.api.libs.json.{JsResult, JsValue}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success, Try}

object LobbyProcessingEngineSpec {
  @DataPoints
  def jsonExampleSeq: Array[LobbyExample] = Array[LobbyExample](
    AdvancedSearch("advancedSearch.json"),
    BuildVillage("buildVillage.json"),
    ChangeLang("changeLang.json"),
    ChangeUserEmail("changeUserEmail.json"),
    ChangeUserName("changeUserName.json"),
    ChangeUserPassword("changeUserPassword.json"),
    EnterLobby("enterLobbyForAnonymousAudience.json"),
    EnterLobby("enterLobbyForHumanPlayer.json"),
    EnterLobby("enterLobbyForOnymousAudience.json"),
    GetAvatarInfo("getAvatar.json"),
    GetSettings("getSettings.json"),
    IdSearch("idSearch.json"),
    KickOutPlayer("kickOutPlayer.json"),
    LeaveWaitingPage("leaveWaitingPage.json"),
    Play("play.json"),
    Pong("pong.json"),
    Ready("ready.json"),
    SelectVillage("selectVillageForHumanPlayer.json"),
    AvatarInfo("avatar.json"),
    //Lobby("lobbyForHumanPlayer.json"),
    Ping("ping.json"),
    //PlayedWithToken("played.json"),
    //SearchResult("searchResult.json"),
    Settings("settings.json"),
    //WaitingPage("waitingPageForHumanPlayer.json")
  )
}

@RunWith(classOf[Theories])
class LobbyProcessingEngineSpec extends AssertionsForJUnit {
  private val processingEngine = new LobbyProcessingEngine(
    new PongAE(),
    new PingAE(),
    new WaitingPageAE(),
    new LobbyAE(),
    new EnterLobbyAE(),
    new GetAvatarInfoAE(),
    new AvatarInfoAE(),
    new SelectVillageAE(),
    new LeaveWaitingPageAE(),
    new KickOutPlayerAE(),
    new BuildVillageAE(),
    new AdvancedSearchAE(),
    new IdSearchAE(),
    new PlayAE(),
    new PlayedWithTokenAE(),
    new ReadyAE(),
    new SearchResultAE(),
    new ChangeLangAE(),
    new ChangeUserEmailAE(),
    new ChangeUserNameAE(),
    new ChangeUserPasswordAE(),
    new GetSettingsAE(),
    new SettingsAE()
  )

  @Theory
  def process(jsonExample: LobbyExample): Unit = {
    val jsonType: String = jsonExample.`type`
    val url: String = jsonExample.path
    implicit val codec: Codec = Codec(StandardCharsets.UTF_8)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()

    processingEngine.process(new LobbyBox(jsonType), msg) match {
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
