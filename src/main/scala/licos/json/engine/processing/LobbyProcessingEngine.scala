package licos.json.engine.processing

import com.typesafe.scalalogging.Logger
import licos.json.element.lobby.client2server.{
  JsonAdvancedSearch,
  JsonAuthorizationRequestAccepted,
  JsonBuildVillage,
  JsonChangeLang,
  JsonChangeUserEmail,
  JsonChangeUserName,
  JsonChangeUserPassword,
  JsonEnterLobby,
  JsonGetSettings,
  JsonIdSearch,
  JsonKickOutPlayer,
  JsonLeaveWaitingPage,
  JsonPlay,
  JsonPong,
  JsonReady,
  JsonRenewAvatarToken,
  JsonSelectVillage
}
import licos.json.element.lobby.server2client.{
  JsonAuthorizationRequest,
  JsonAuthorizationRequestAcceptedResponse,
  JsonAvatarInfo,
  JsonGetAvatarInfo,
  JsonLobby,
  JsonNewAvatarToken,
  JsonPing,
  JsonPlayed,
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
  * @param changeLangEngine the analysis engine for Change-lang JSON.
  * @param changeUserEmailEngine the analysis engine for Change-user-email JSON.
  * @param changeUserNameEngine the analysis engine for Change-user-name JSON.
  * @param changeUserPasswordEngine the analysis engine for Change-user-password JSON.
  * @param getSettingsEngine the analysis engine for Get-settings JSON.
  * @param settingsEngine the analysis engine for Settings JSON.
  * @param authorizationRequestEngine the analysis engine for Authorization-request JSON
  * @param authorizationRequestAcceptedResponseEngine the analysis engine for Authorization-request-accepted-response JSON
  * @param authorizationRequestAcceptedEngine the analysis engine for Authorization-request-accepted JSON
  * @param renewAvatarTokenAnalysisEngine the analysis engine for Renew-avatar-token JSON
  * @param newAvatarTokenAnalysisEngine the analysis engine for New-avatar-token JSON
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
    changeLangEngine:                           Option[ChangeLangAnalysisEngine],
    changeUserEmailEngine:                      Option[ChangeUserEmailAnalysisEngine],
    changeUserNameEngine:                       Option[ChangeUserNameAnalysisEngine],
    changeUserPasswordEngine:                   Option[ChangeUserPasswordAnalysisEngine],
    getSettingsEngine:                          Option[GetSettingsAnalysisEngine],
    settingsEngine:                             Option[SettingsAnalysisEngine],
    authorizationRequestEngine:                 Option[AuthorizationRequestAnalysisEngine],
    authorizationRequestAcceptedResponseEngine: Option[AuthorizationRequestAcceptedResponseAnalysisEngine],
    authorizationRequestAcceptedEngine:         Option[AuthorizationRequestAcceptedAnalysisEngine],
    renewAvatarTokenAnalysisEngine:             Option[RenewAvatarTokenAnalysisEngine],
    newAvatarTokenAnalysisEngine:               Option[NewAvatarTokenAnalysisEngine]
) extends ProcessingEngine {

  override protected val flowController = new LobbyFlowController()

  private final val logger = Logger[LobbyProcessingEngine]

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
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None
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
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None
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
      case Right(changeLang: JsonChangeLang) =>
        log("JsonChangeLang")
        changeLangEngine match {
          case Some(engine) =>
            engine.process(box, changeLang)
          case None =>
            noAnalysisEngine(ChangeLangAnalysisEngine.name, ChangeLangAnalysisEngine.isFromServer)
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
        renewAvatarTokenAnalysisEngine match {
          case Some(engine) =>
            engine.process(box, renewAvatarToken)
          case None =>
            noAnalysisEngine(
              RenewAvatarTokenAnalysisEngine.name,
              RenewAvatarTokenAnalysisEngine.isFromServer
            )
        }
      case Right(newAvatarToken: JsonNewAvatarToken) =>
        log("JsonNewAvatarToken")
        newAvatarTokenAnalysisEngine match {
          case Some(engine) =>
            engine.process(box, newAvatarToken)
          case None =>
            noAnalysisEngine(
              NewAvatarTokenAnalysisEngine.name,
              NewAvatarTokenAnalysisEngine.isFromServer
            )
        }
      case _ =>
        log("return nothing")
        otherwise
    }
  }
}
