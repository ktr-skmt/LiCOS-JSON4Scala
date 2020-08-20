package protocol.engine.async.lobby

import java.net.URL
import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import licos.json.parser.LobbyParser
import licos.json2protocol.lobby.Json2LobbyMessageProtocol
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.engine.async.processing.lobby.{LobbyProcessingEngine, LobbyProcessingEngineFactory}
import licos.protocol.engine.async.processing.{LobbyPE, SpecificProcessingEngineFactory}
import org.scalatest.{FunSuite, Matchers}
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor1}
import play.api.libs.json.Json
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.LobbyExample
import protocol.engine.async.lobby.analysis.client2server.{
  AdvancedSearchAE,
  AuthorizationRequestAcceptedAE,
  BuildVillageAE,
  ChangeAvatarAE,
  ChangeLanguageAE,
  ChangeUserEmailAE,
  ChangeUserNameAE,
  ChangeUserPasswordAE,
  CreateHumanPlayerAE,
  CreateOnymousAudienceAE,
  CreateRobotPlayerAE,
  DeleteAvatarAE,
  EnterAvatarSelectionPageAE,
  EnterLobbyAE,
  GetAvatarInfoAE,
  GetSettingsAE,
  IdSearchAE,
  KickOutPlayerAE,
  LeaveWaitingPageAE,
  PlayAE,
  PongAE,
  ReadyAE,
  RenewAvatarTokenAE,
  RunRobotPlayerInTheBackgroundAE,
  SelectVillageAE,
  StopRobotPlayerAE
}
import protocol.engine.async.lobby.analysis.server2client.{
  AuthorizationRequestAE,
  AuthorizationRequestAcceptedResponseAE,
  AvatarInfoAE,
  HumanPlayerSelectionPageAE,
  LobbyAE,
  OnymousAudienceSelectionPageAE,
  PingAE,
  PlayedAE,
  RobotPlayerSelectionPageAE,
  SearchResultAE,
  SettingsAE,
  WaitingPageAE
}
import protocol.engine.async.lobby.analysis.server2server.PlayedWithTokenAE
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.{
  AdvancedSearch,
  AuthorizationRequestAccepted,
  BuildVillage,
  ChangeAvatar,
  ChangeLanguage,
  ChangeUserEmail,
  ChangeUserName,
  ChangeUserPassword,
  CreateHumanPlayer,
  CreateOnymousAudience,
  CreateRobotPlayer,
  DeleteAvatar,
  EnterAvatarSelectionPage,
  EnterLobby,
  GetAvatarInfo,
  GetSettings,
  IdSearch,
  KickOutPlayer,
  LeaveWaitingPage,
  Play,
  Pong,
  Ready,
  RenewAvatarToken,
  RunRobotPlayerInTheBackground,
  SelectVillage,
  StopRobotPlayer
}
import protocol.engine.lobby.example.server2client.{
  AuthorizationRequest,
  AuthorizationRequestAcceptedResponse,
  AvatarInfo,
  HumanPlayerSelectionPage,
  Lobby,
  OnymousAudienceSelectionPage,
  Ping,
  Played,
  RobotPlayerSelectionPage,
  SearchResult,
  Settings,
  WaitingPage
}
import protocol.engine.lobby.example.server2server.PlayedWithToken

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.{Codec, Source}

final class LobbyProcessingEngineSuite extends FunSuite with Matchers with TableDrivenPropertyChecks with LobbyParser {

  private val fractions: TableFor1[LobbyExample] =
    Table(
      "jsonExample",
      AdvancedSearch("advancedSearch.json"),
      BuildVillage("buildVillage.json"),
      ChangeLanguage("changeLanguage.json"),
      ChangeUserEmail("changeUserEmail.json"),
      ChangeUserName("changeUserName.json"),
      ChangeUserPassword("changeUserPassword.json"),
      EnterLobby("enterLobbyForAnonymousAudience.json"),
      EnterLobby("enterLobbyForHumanPlayer.json"),
      EnterLobby("enterLobbyForOnymousAudience.json"),
      EnterLobby("enterLobbyForRobotPlayer.json"),
      GetAvatarInfo("getAvatar.json"),
      GetSettings("getSettings.json"),
      IdSearch("idSearch.json"),
      KickOutPlayer("kickOutPlayer.json"),
      LeaveWaitingPage("leaveWaitingPage.json"),
      Play("play.json"),
      Pong("pong.json"),
      Ready("ready.json"),
      SelectVillage("selectVillage.json"),
      AuthorizationRequest("authorizationRequest.json"),
      AuthorizationRequestAcceptedResponse("authorizationRequestAcceptedResponse.json"),
      AvatarInfo("avatar.json"),
      Lobby("lobbyForAnonymousAudience.json"),
      Lobby("lobbyForHumanPlayer.json"),
      Lobby("lobbyForOnymousAudience.json"),
      Lobby("lobbyForRobotPlayer.json"),
      Ping("ping.json"),
      Played("played.json"),
      SearchResult("searchResult.json"),
      Settings("settings.json"),
      WaitingPage("waitingPage.json"),
      PlayedWithToken("playedWithToken.json"),
      AuthorizationRequestAccepted("authorizationRequestAccepted.json"),
      RenewAvatarToken("renewAvatarToken.json"),
      CreateHumanPlayer("createHumanPlayer.json"),
      CreateOnymousAudience("createOnymousAudience.json"),
      CreateRobotPlayer("createRobotPlayer.json"),
      DeleteAvatar("deleteAvatar.json"),
      RunRobotPlayerInTheBackground("runRobotPlayerInTheBackground.json"),
      StopRobotPlayer("stopRobotPlayer.json"),
      HumanPlayerSelectionPage("humanPlayerSelectionPage.json"),
      OnymousAudienceSelectionPage("onymousAudienceSelectionPage.json"),
      RobotPlayerSelectionPage("robotPlayerSelectionPage.json"),
      ChangeAvatar("updateAvatarName.json"),
      ChangeAvatar("updateAvatarImage.json"),
      ChangeAvatar("updateAvatarLanguage.json"),
      EnterAvatarSelectionPage("enterAvatarSelectionPage.json")
    )

  private val log: Logger = Logger[LobbyProcessingEngineSuite]

  private val processingEngineFactory: LobbyProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(LobbyPE)
    .asInstanceOf[LobbyProcessingEngineFactory]
    .set(new AdvancedSearchAE())
    .set(new AvatarInfoAE())
    .set(new BuildVillageAE())
    .set(new ChangeLanguageAE())
    .set(new ChangeUserEmailAE())
    .set(new ChangeUserNameAE())
    .set(new ChangeUserPasswordAE())
    .set(new EnterLobbyAE())
    .set(new GetAvatarInfoAE())
    .set(new GetSettingsAE())
    .set(new IdSearchAE())
    .set(new KickOutPlayerAE())
    .set(new LeaveWaitingPageAE())
    .set(new LobbyAE())
    .set(new PingAE())
    .set(new PlayAE())
    .set(new PlayedAE())
    .set(new PlayedWithTokenAE())
    .set(new PongAE())
    .set(new ReadyAE())
    .set(new SearchResultAE())
    .set(new SelectVillageAE())
    .set(new SettingsAE())
    .set(new WaitingPageAE())
    .set(new AuthorizationRequestAE())
    .set(new AuthorizationRequestAcceptedResponseAE())
    .set(new AuthorizationRequestAcceptedAE())
    .set(new RenewAvatarTokenAE())
    .set(new CreateHumanPlayerAE())
    .set(new CreateOnymousAudienceAE())
    .set(new CreateRobotPlayerAE())
    .set(new DeleteAvatarAE())
    .set(new RunRobotPlayerInTheBackgroundAE())
    .set(new StopRobotPlayerAE())
    .set(new HumanPlayerSelectionPageAE())
    .set(new OnymousAudienceSelectionPageAE())
    .set(new RobotPlayerSelectionPageAE())
    .set(new ChangeAvatarAE())
    .set(new EnterAvatarSelectionPageAE())

  private val processingEngine: LobbyProcessingEngine = processingEngineFactory.create

  test("protocol.async.lobbyProcessingEngineSuite") {
    forEvery(fractions) { jsonExample: LobbyExample =>
      val jsonType:       String = jsonExample.`type`
      val url:            URL    = jsonExample.path
      implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
      log.info(url.toString)
      val source = Source.fromURL(url)
      val msg: String = source.getLines.mkString("\n")
      source.close()
      log.debug(msg)

      import scala.concurrent.ExecutionContext.Implicits.global

      Json2LobbyMessageProtocol.toProtocolOpt(Json.parse(msg)) match {
        case Some(protocol: LobbyMessageProtocol) =>
          Await.ready(
            processingEngine
              .process(new LobbyBox(), protocol)
              .map { messageProtocol: LobbyMessageProtocol =>
                messageProtocol match {
                  case p: LobbyMessageTestProtocol =>
                    p.text shouldBe jsonType
                  case _ =>
                    fail("No LobbyMessageTestProtocol")
                }
              }
              .recover {
                case error: Throwable =>
                  fail(
                    List[String](
                      "No response is generated.",
                      error.getMessage,
                      msg
                    ).mkString("\n")
                  )
              },
            Duration.Inf
          )
        case _ =>
          fail("No protocol")
      }
    }
  }
}
