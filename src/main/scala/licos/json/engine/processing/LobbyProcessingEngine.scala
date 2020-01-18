package licos.json.engine.processing

import com.typesafe.scalalogging.Logger
import licos.json.element.lobby.client2server.{
  JsonAdvancedSearch,
  JsonAuthorizationRequestAccepted,
  JsonBuildVillage,
  JsonChangeLanguage,
  JsonChangeUserEmail,
  JsonChangeUserName,
  JsonChangeUserPassword,
  JsonCreateHumanPlayer,
  JsonCreateOnymousAudience,
  JsonCreateRobotPlayer,
  JsonDeleteAvatar,
  JsonEnterAvatarSelectionPage,
  JsonEnterLobby,
  JsonGetSettings,
  JsonIdSearch,
  JsonKickOutPlayer,
  JsonLeaveWaitingPage,
  JsonPlay,
  JsonPong,
  JsonReady,
  JsonRenewAvatarToken,
  JsonRunRobotPlayerInTheBackground,
  JsonSelectVillage,
  JsonStopRobotPlayer,
  JsonUpdateAvatar
}
import licos.json.element.lobby.server2client.{
  JsonAuthorizationRequest,
  JsonAuthorizationRequestAcceptedResponse,
  JsonAvatarInfo,
  JsonGetAvatarInfo,
  JsonHumanPlayerSelectionPage,
  JsonLobby,
  JsonOnymousAudienceSelectionPage,
  JsonPing,
  JsonPlayed,
  JsonRobotPlayerSelectionPage,
  JsonSearchResult,
  JsonSettings,
  JsonWaitingPage
}
import licos.json.element.lobby.server2server.JsonPlayedWithToken
import licos.json.element.village.{JsonName, JsonSubError}
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server._
import licos.json.engine.analysis.lobby.server2client._
import licos.json.engine.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine
import licos.json.flow.LobbyFlowController
import play.api.libs.json.{JsValue, Json}

/** This class implements the processing engine that aggregates and runs analysis engines for lobby.
  *
  * @param pongEngine the analysis engine for Pong JSON.
  * @param pingEngine the analysis engine for Ping JSON.
  * @param waitingPageEngine the analysis engine for Waiting-page JSON.
  * @param lobbyEngine the analysis engine for Lobby JSON.
  * @param enterLobbyEngine the analysis engine for Enter-lobby JSON.
  * @param getAvatarInfoEngine the analysis engine for Get-avatar-info JSON.
  * @param avatarInfoEngine the analysis engine for Avatar-info JSON.
  * @param selectVillageEngine the analysis engine for Select-village JSON.
  * @param leaveWaitingPageEngine the analysis engine for Leave-waiting-page JSON.
  * @param kickOutPlayerEngine the analysis engine for Kick-out-player JSON.
  * @param buildVillageEngine the analysis engine for Build-village JSON.
  * @param advancedSearchEngine the analysis engine for Advanced-search JSON.
  * @param idSearchEngine the analysis engine for Id-search JSON.
  * @param playEngine the analysis engine for play JSON.
  * @param playedEngine the analysis engine for Played JSON.
  * @param playedWithTokenEngine the analysis engine for Played-with-token JSON.
  * @param readyEngine the analysis engine for Ready JSON.
  * @param searchResultEngine the analysis engine for Search-result JSON.
  * @param changeLanguageEngine the analysis engine for Change-language JSON.
  * @param changeUserEmailEngine the analysis engine for Change-user-email JSON.
  * @param changeUserNameEngine the analysis engine for Change-user-name JSON.
  * @param changeUserPasswordEngine the analysis engine for Change-user-password JSON.
  * @param getSettingsEngine the analysis engine for Get-settings JSON.
  * @param settingsEngine the analysis engine for Settings JSON.
  * @param authorizationRequestEngine the analysis engine for Authorization-request JSON
  * @param authorizationRequestAcceptedResponseEngine the analysis engine for Authorization-request-accepted-response JSON
  * @param authorizationRequestAcceptedEngine the analysis engine for Authorization-request-accepted JSON
  * @param renewAvatarTokenEngine the analysis engine for Renew-avatar-token JSON
  * @param createHumanPlayerEngine the analysis engine for Create-human-player JSON
  * @param createRobotPlayerEngine the analysis engine for Create-robot-player JSON
  * @param createOnymousAudienceEngine the analysis engine for Create-onymous-audience JSON
  * @param deleteAvatarEngine the analysis engine for Delete-avatar JSON
  * @param runRobotPlayerInTheBackgroundEngine the analysis engine for Run-robot-player-in-the-background JSON
  * @param stopRobotPlayerEngine the analysis engine for Stop-robot-player JSON
  * @param humanPlayerSelectionPageEngine the analysis engine for Human-player-selection-page JSON
  * @param onymousAudienceSelectionPageEngine the analysis engine for Onymous-audience-selection-page JSON
  * @param robotPlayerSelectionPageEngine the analysis engine for Robot-player-selection-page JSON
  * @param updateAvatarEngine the analysis engine for Update-avatar JSON
  * @param enterAvatarSelectionPageEngine the analysis engine for Enter-avatar-selection-page JSON
  * @author Kotaro Sakamoto
  */
final class LobbyProcessingEngine(
    pongEngine:                                 Option[PongAnalysisEngine],
    pingEngine:                                 Option[PingAnalysisEngine],
    waitingPageEngine:                          Option[WaitingPageAnalysisEngine],
    lobbyEngine:                                Option[LobbyAnalysisEngine],
    enterLobbyEngine:                           Option[EnterLobbyAnalysisEngine],
    getAvatarInfoEngine:                        Option[GetAvatarInfoAnalysisEngine],
    avatarInfoEngine:                           Option[AvatarInfoAnalysisEngine],
    selectVillageEngine:                        Option[SelectVillageAnalysisEngine],
    leaveWaitingPageEngine:                     Option[LeaveWaitingPageAnalysisEngine],
    kickOutPlayerEngine:                        Option[KickOutPlayerAnalysisEngine],
    buildVillageEngine:                         Option[BuildVillageAnalysisEngine],
    advancedSearchEngine:                       Option[AdvancedSearchAnalysisEngine],
    idSearchEngine:                             Option[IdSearchAnalysisEngine],
    playEngine:                                 Option[PlayAnalysisEngine],
    playedEngine:                               Option[PlayedAnalysisEngine],
    playedWithTokenEngine:                      Option[PlayedWithTokenAnalysisEngine],
    readyEngine:                                Option[ReadyAnalysisEngine],
    searchResultEngine:                         Option[SearchResultAnalysisEngine],
    changeLanguageEngine:                       Option[ChangeLanguageAnalysisEngine],
    changeUserEmailEngine:                      Option[ChangeUserEmailAnalysisEngine],
    changeUserNameEngine:                       Option[ChangeUserNameAnalysisEngine],
    changeUserPasswordEngine:                   Option[ChangeUserPasswordAnalysisEngine],
    getSettingsEngine:                          Option[GetSettingsAnalysisEngine],
    settingsEngine:                             Option[SettingsAnalysisEngine],
    authorizationRequestEngine:                 Option[AuthorizationRequestAnalysisEngine],
    authorizationRequestAcceptedResponseEngine: Option[AuthorizationRequestAcceptedResponseAnalysisEngine],
    authorizationRequestAcceptedEngine:         Option[AuthorizationRequestAcceptedAnalysisEngine],
    renewAvatarTokenEngine:                     Option[RenewAvatarTokenAnalysisEngine],
    createHumanPlayerEngine:                    Option[CreateHumanPlayerAnalysisEngine],
    createOnymousAudienceEngine:                Option[CreateOnymousAudienceAnalysisEngine],
    createRobotPlayerEngine:                    Option[CreateRobotPlayerAnalysisEngine],
    deleteAvatarEngine:                         Option[DeleteAvatarAnalysisEngine],
    runRobotPlayerInTheBackgroundEngine:        Option[RunRobotPlayerInTheBackgroundAnalysisEngine],
    stopRobotPlayerEngine:                      Option[StopRobotPlayerAnalysisEngine],
    humanPlayerSelectionPageEngine:             Option[HumanPlayerSelectionPageAnalysisEngine],
    onymousAudienceSelectionPageEngine:         Option[OnymousAudienceSelectionPageAnalysisEngine],
    robotPlayerSelectionPageEngine:             Option[RobotPlayerSelectionPageAnalysisEngine],
    updateAvatarEngine:                         Option[UpdateAvatarAnalysisEngine],
    enterAvatarSelectionPageEngine:             Option[EnterAvatarSelectionPageAnalysisEngine]
) extends ProcessingEngine {

  override protected val flowController = new LobbyFlowController()

  private val logger = Logger[LobbyProcessingEngine]

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param msg a JSON message.
    * @return a play.api.libs.json.JsValue Either. Right(json: JsValue) if succeeded, Left(error: JsValue) if failed.
    */
  override def process(box: BOX, msg: String): Either[JsValue, JsValue] = {

    val jsValue: JsValue = Json.parse(msg)

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
    def noAnalysisEngine(name: String, isFromServer: Boolean): Either[JsValue, JsValue] = {
      Left(
        Json.toJson(
          new JsonSubError(
            new JsonName(
              en = s"No $name is set. Please set it to the processing engine.",
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            ),
            "warning",
            "Nothing",
            isFromServer
          )
        )
      )
    }

    @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
    def otherwise: Either[JsValue, JsValue] = {
      Right(
        Json.toJson(
          new JsonSubError(
            new JsonName(
              en = "LobbyProcessingEngine returns nothing",
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            ),
            "warning",
            jsValue.toString,
            isFromServer = true
          )
        )
      )
    }

    flowController.flow(jsValue) match {
      case Right(pong: JsonPong) =>
        log("JsonPong")
        pongEngine match {
          case Some(engine) =>
            engine.process(box, pong)
          case None =>
            noAnalysisEngine(PongAnalysisEngine.name, PongAnalysisEngine.isFromServer)
        }
      case Right(ping: JsonPing) =>
        log("JsonPing")
        pingEngine match {
          case Some(engine) =>
            engine.process(box, ping)
          case None =>
            noAnalysisEngine(PingAnalysisEngine.name, PingAnalysisEngine.isFromServer)
        }
      case Right(waitingPage: JsonWaitingPage) =>
        log("JsonWaitingPage")
        waitingPageEngine match {
          case Some(engine) =>
            engine.process(box, waitingPage)
          case None =>
            noAnalysisEngine(WaitingPageAnalysisEngine.name, WaitingPageAnalysisEngine.isFromServer)
        }
      case Right(lobby: JsonLobby) =>
        log("JsonLobby")
        lobbyEngine match {
          case Some(engine) =>
            engine.process(box, lobby)
          case None =>
            noAnalysisEngine(LobbyAnalysisEngine.name, LobbyAnalysisEngine.isFromServer)
        }
      case Right(enterLobby: JsonEnterLobby) =>
        log("JsonEnterLobby")
        enterLobbyEngine match {
          case Some(engine) =>
            engine.process(box, enterLobby)
          case None =>
            noAnalysisEngine(EnterLobbyAnalysisEngine.name, EnterLobbyAnalysisEngine.isFromServer)
        }
      case Right(getAvatarInfo: JsonGetAvatarInfo) =>
        log("JsonGetAvatarInfo")
        getAvatarInfoEngine match {
          case Some(engine) =>
            engine.process(box, getAvatarInfo)
          case None =>
            noAnalysisEngine(GetAvatarInfoAnalysisEngine.name, GetAvatarInfoAnalysisEngine.isFromServer)
        }
      case Right(avatarInfo: JsonAvatarInfo) =>
        log("JsonAvatarInfo")
        avatarInfoEngine match {
          case Some(engine) =>
            engine.process(box, avatarInfo)
          case None =>
            noAnalysisEngine(AvatarInfoAnalysisEngine.name, AvatarInfoAnalysisEngine.isFromServer)
        }
      case Right(selectVillage: JsonSelectVillage) =>
        log("JsonSelectVillage")
        selectVillageEngine match {
          case Some(engine) =>
            engine.process(box, selectVillage)
          case None =>
            noAnalysisEngine(SelectVillageAnalysisEngine.name, SelectVillageAnalysisEngine.isFromServer)
        }
      case Right(leaveWaitingPage: JsonLeaveWaitingPage) =>
        log("JsonLeaveWaitingPage")
        leaveWaitingPageEngine match {
          case Some(engine) =>
            engine.process(box, leaveWaitingPage)
          case None =>
            noAnalysisEngine(LeaveWaitingPageAnalysisEngine.name, LeaveWaitingPageAnalysisEngine.isFromServer)
        }
      case Right(kickOutPlayer: JsonKickOutPlayer) =>
        log("JsonKickOutPlayer")
        kickOutPlayerEngine match {
          case Some(engine) =>
            engine.process(box, kickOutPlayer)
          case None =>
            noAnalysisEngine(KickOutPlayerAnalysisEngine.name, KickOutPlayerAnalysisEngine.isFromServer)
        }
      case Right(buildVillage: JsonBuildVillage) =>
        log("JsonBuildVillage")
        buildVillageEngine match {
          case Some(engine) =>
            engine.process(box, buildVillage)
          case None =>
            noAnalysisEngine(BuildVillageAnalysisEngine.name, BuildVillageAnalysisEngine.isFromServer)
        }
      case Right(advancedSearch: JsonAdvancedSearch) =>
        log("JsonAdvancedSearch")
        advancedSearchEngine match {
          case Some(engine) =>
            engine.process(box, advancedSearch)
          case None =>
            noAnalysisEngine(AdvancedSearchAnalysisEngine.name, AdvancedSearchAnalysisEngine.isFromServer)
        }
      case Right(idSearch: JsonIdSearch) =>
        log("JsonIdSearch")
        idSearchEngine match {
          case Some(engine) =>
            engine.process(box, idSearch)
          case None =>
            noAnalysisEngine(IdSearchAnalysisEngine.name, IdSearchAnalysisEngine.isFromServer)
        }
      case Right(play: JsonPlay) =>
        log("JsonPlay")
        playEngine match {
          case Some(engine) =>
            engine.process(box, play)
          case None =>
            noAnalysisEngine(PlayAnalysisEngine.name, PlayAnalysisEngine.isFromServer)
        }
      case Right(played: JsonPlayed) =>
        log("JsonPlayed")
        playedEngine match {
          case Some(engine) =>
            engine.process(box, played)
          case None =>
            noAnalysisEngine(PlayedAnalysisEngine.name, PlayedAnalysisEngine.isFromServer)
        }
      case Right(playedWithToken: JsonPlayedWithToken) =>
        log("JsonPlayedWithToken")
        playedWithTokenEngine match {
          case Some(engine) =>
            engine.process(box, playedWithToken)
          case None =>
            noAnalysisEngine(PlayedWithTokenAnalysisEngine.name, PlayedWithTokenAnalysisEngine.isFromServer)
        }
      case Right(ready: JsonReady) =>
        log("JsonReady")
        readyEngine match {
          case Some(engine) =>
            engine.process(box, ready)
          case None =>
            noAnalysisEngine(ReadyAnalysisEngine.name, ReadyAnalysisEngine.isFromServer)
        }
      case Right(searchResult: JsonSearchResult) =>
        log("JsonSearchResult")
        searchResultEngine match {
          case Some(engine) =>
            engine.process(box, searchResult)
          case None =>
            noAnalysisEngine(SearchResultAnalysisEngine.name, SearchResultAnalysisEngine.isFromServer)
        }
      case Right(changeLanguage: JsonChangeLanguage) =>
        log("JsonChangeLang")
        changeLanguageEngine match {
          case Some(engine) =>
            engine.process(box, changeLanguage)
          case None =>
            noAnalysisEngine(ChangeLanguageAnalysisEngine.name, ChangeLanguageAnalysisEngine.isFromServer)
        }
      case Right(changeUserEmail: JsonChangeUserEmail) =>
        log("JsonChangeUserEmail")
        changeUserEmailEngine match {
          case Some(engine) =>
            engine.process(box, changeUserEmail)
          case None =>
            noAnalysisEngine(ChangeUserEmailAnalysisEngine.name, ChangeUserEmailAnalysisEngine.isFromServer)
        }
      case Right(changeUserName: JsonChangeUserName) =>
        log("JsonChangeUserName")
        changeUserNameEngine match {
          case Some(engine) =>
            engine.process(box, changeUserName)
          case None =>
            noAnalysisEngine(ChangeUserNameAnalysisEngine.name, ChangeUserNameAnalysisEngine.isFromServer)
        }
      case Right(changeUserPassword: JsonChangeUserPassword) =>
        log("JsonChangeUserPassword")
        changeUserPasswordEngine match {
          case Some(engine) =>
            engine.process(box, changeUserPassword)
          case None =>
            noAnalysisEngine(ChangeUserPasswordAnalysisEngine.name, ChangeUserPasswordAnalysisEngine.isFromServer)
        }
      case Right(getSettings: JsonGetSettings) =>
        log("JsonGetSettings")
        getSettingsEngine match {
          case Some(engine) =>
            engine.process(box, getSettings)
          case None =>
            noAnalysisEngine(GetSettingsAnalysisEngine.name, GetSettingsAnalysisEngine.isFromServer)
        }
      case Right(settings: JsonSettings) =>
        log("JsonSettings")
        settingsEngine match {
          case Some(engine) =>
            engine.process(box, settings)
          case None =>
            noAnalysisEngine(SettingsAnalysisEngine.name, GetSettingsAnalysisEngine.isFromServer)
        }
      case Right(authorizationRequest: JsonAuthorizationRequest) =>
        log("JsonAuthorizationRequest")
        authorizationRequestEngine match {
          case Some(engine) =>
            engine.process(box, authorizationRequest)
          case None =>
            noAnalysisEngine(AuthorizationRequestAnalysisEngine.name, AuthorizationRequestAnalysisEngine.isFromServer)
        }
      case Right(authorizationRequestAcceptedResponse: JsonAuthorizationRequestAcceptedResponse) =>
        log("JsonAuthorizationRequestAcceptedResponse")
        authorizationRequestAcceptedResponseEngine match {
          case Some(engine) =>
            engine.process(box, authorizationRequestAcceptedResponse)
          case None =>
            noAnalysisEngine(
              AuthorizationRequestAcceptedResponseAnalysisEngine.name,
              AuthorizationRequestAcceptedResponseAnalysisEngine.isFromServer
            )
        }
      case Right(authorizationRequestAccepted: JsonAuthorizationRequestAccepted) =>
        log("JsonAuthorizationRequestAccepted")
        authorizationRequestAcceptedEngine match {
          case Some(engine) =>
            engine.process(box, authorizationRequestAccepted)
          case None =>
            noAnalysisEngine(
              AuthorizationRequestAcceptedAnalysisEngine.name,
              AuthorizationRequestAcceptedAnalysisEngine.isFromServer
            )
        }
      case Right(renewAvatarToken: JsonRenewAvatarToken) =>
        log("JsonRenewAvatarToken")
        renewAvatarTokenEngine match {
          case Some(engine) =>
            engine.process(box, renewAvatarToken)
          case None =>
            noAnalysisEngine(
              RenewAvatarTokenAnalysisEngine.name,
              RenewAvatarTokenAnalysisEngine.isFromServer
            )
        }
      case Right(createHumanPlayer: JsonCreateHumanPlayer) =>
        log("JsonCreateHumanPlayer")
        createHumanPlayerEngine match {
          case Some(engine) =>
            engine.process(box, createHumanPlayer)
          case None =>
            noAnalysisEngine(
              CreateHumanPlayerAnalysisEngine.name,
              CreateHumanPlayerAnalysisEngine.isFromServer
            )
        }
      case Right(createOnymousAudience: JsonCreateOnymousAudience) =>
        log("JsonCreateOnymousAudience")
        createOnymousAudienceEngine match {
          case Some(engine) =>
            engine.process(box, createOnymousAudience)
          case None =>
            noAnalysisEngine(
              CreateOnymousAudienceAnalysisEngine.name,
              CreateOnymousAudienceAnalysisEngine.isFromServer
            )
        }
      case Right(createRobotPlayer: JsonCreateRobotPlayer) =>
        log("JsonCreateRobotPlayer")
        createRobotPlayerEngine match {
          case Some(engine) =>
            engine.process(box, createRobotPlayer)
          case None =>
            noAnalysisEngine(
              CreateRobotPlayerAnalysisEngine.name,
              CreateRobotPlayerAnalysisEngine.isFromServer
            )
        }
      case Right(deleteAvatar: JsonDeleteAvatar) =>
        log("JsonDeleteAvatar")
        deleteAvatarEngine match {
          case Some(engine) =>
            engine.process(box, deleteAvatar)
          case None =>
            noAnalysisEngine(
              DeleteAvatarAnalysisEngine.name,
              DeleteAvatarAnalysisEngine.isFromServer
            )
        }
      case Right(runRobotPlayerInTheBackground: JsonRunRobotPlayerInTheBackground) =>
        log("JsonRunRobotPlayerInTheBackground")
        runRobotPlayerInTheBackgroundEngine match {
          case Some(engine) =>
            engine.process(box, runRobotPlayerInTheBackground)
          case None =>
            noAnalysisEngine(
              RunRobotPlayerInTheBackgroundAnalysisEngine.name,
              RunRobotPlayerInTheBackgroundAnalysisEngine.isFromServer
            )
        }
      case Right(stopRobotPlayer: JsonStopRobotPlayer) =>
        log("JsonStopRobotPlayer")
        stopRobotPlayerEngine match {
          case Some(engine) =>
            engine.process(box, stopRobotPlayer)
          case None =>
            noAnalysisEngine(
              StopRobotPlayerAnalysisEngine.name,
              StopRobotPlayerAnalysisEngine.isFromServer
            )
        }
      case Right(humanPlayerSelectionPage: JsonHumanPlayerSelectionPage) =>
        log("JsonHumanPlayerSelectionPage")
        humanPlayerSelectionPageEngine match {
          case Some(engine) =>
            engine.process(box, humanPlayerSelectionPage)
          case None =>
            noAnalysisEngine(
              HumanPlayerSelectionPageAnalysisEngine.name,
              HumanPlayerSelectionPageAnalysisEngine.isFromServer
            )
        }
      case Right(onymousAudienceSelectionPage: JsonOnymousAudienceSelectionPage) =>
        log("JsonOnymousAudienceSelectionPage")
        onymousAudienceSelectionPageEngine match {
          case Some(engine) =>
            engine.process(box, onymousAudienceSelectionPage)
          case None =>
            noAnalysisEngine(
              OnymousAudienceSelectionPageAnalysisEngine.name,
              OnymousAudienceSelectionPageAnalysisEngine.isFromServer
            )
        }
      case Right(robotPlayerSelectionPage: JsonRobotPlayerSelectionPage) =>
        log("JsonRobotPlayerSelectionPage")
        robotPlayerSelectionPageEngine match {
          case Some(engine) =>
            engine.process(box, robotPlayerSelectionPage)
          case None =>
            noAnalysisEngine(
              RobotPlayerSelectionPageAnalysisEngine.name,
              RobotPlayerSelectionPageAnalysisEngine.isFromServer
            )
        }
      case Right(updateAvatar: JsonUpdateAvatar) =>
        log("JsonUpdateAvatar")
        updateAvatarEngine match {
          case Some(engine) =>
            engine.process(box, updateAvatar)
          case None =>
            noAnalysisEngine(
              UpdateAvatarAnalysisEngine.name,
              UpdateAvatarAnalysisEngine.isFromServer
            )
        }
      case Right(enterAvatarSelectionPage: JsonEnterAvatarSelectionPage) =>
        log("JsonEnterAvatarSelectionPage")
        enterAvatarSelectionPageEngine match {
          case Some(engine) =>
            engine.process(box, enterAvatarSelectionPage)
          case None =>
            noAnalysisEngine(
              EnterAvatarSelectionPageAnalysisEngine.name,
              EnterAvatarSelectionPageAnalysisEngine.isFromServer
            )
        }
      case _ =>
        log("return nothing")
        otherwise
    }
  }
}
