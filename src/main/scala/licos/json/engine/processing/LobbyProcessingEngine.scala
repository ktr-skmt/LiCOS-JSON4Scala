package licos.json.engine.processing

import com.typesafe.scalalogging.Logger
import licos.json.element.lobby.{JsonAdvancedSearch, JsonAvatarInfo, JsonBuildVillage, JsonChangeLang, JsonChangeUserEmail, JsonChangeUserName, JsonChangeUserPassword, JsonEnterLobby, JsonGetAvatarInfo, JsonGetSettings, JsonIdSearch, JsonKickOutPlayer, JsonLeaveWaitingPage, JsonLobby, JsonPing, JsonPlay, JsonPlayed, JsonPlayedWithToken, JsonPong, JsonReady, JsonSearchResult, JsonSelectVillage, JsonSettings, JsonWaitingPage}
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server._
import licos.json.engine.analysis.lobby.server2client._
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
  * @author Kotaro Sakamoto
  */
class LobbyProcessingEngine(pongEngine: Option[PongAnalysisEngine],
                            pingEngine: Option[PingAnalysisEngine],
                            waitingPageEngine: Option[WaitingPageAnalysisEngine],
                            lobbyEngine: Option[LobbyAnalysisEngine],
                            enterLobbyEngine: Option[EnterLobbyAnalysisEngine],
                            getAvatarInfoEngine: Option[GetAvatarInfoAnalysisEngine],
                            avatarInfoEngine: Option[AvatarInfoAnalysisEngine],
                            selectVillageEngine: Option[SelectVillageAnalysisEngine],
                            leaveWaitingPageEngine: Option[LeaveWaitingPageAnalysisEngine],
                            kickOutPlayerEngine: Option[KickOutPlayerAnalysisEngine],
                            buildVillageEngine: Option[BuildVillageAnalysisEngine],
                            advancedSearchEngine: Option[AdvancedSearchAnalysisEngine],
                            idSearchEngine: Option[IdSearchAnalysisEngine],
                            playEngine: Option[PlayAnalysisEngine],
                            playedEngine: Option[PlayedAnalysisEngine],
                            playedWithTokenEngine: Option[PlayedWithTokenAnalysisEngine],
                            readyEngine: Option[ReadyAnalysisEngine],
                            searchResultEngine: Option[SearchResultAnalysisEngine],
                            changeLangEngine: Option[ChangeLangAnalysisEngine],
                            changeUserEmailEngine: Option[ChangeUserEmailAnalysisEngine],
                            changeUserNameEngine: Option[ChangeUserNameAnalysisEngine],
                            changeUserPasswordEngine: Option[ChangeUserPasswordAnalysisEngine],
                            getSettingsEngine: Option[GetSettingsAnalysisEngine],
                            settingsEngine: Option[SettingsAnalysisEngine]) extends ProcessingEngine {

  override protected val flowController = new LobbyFlowController()

  private final val logger = Logger[LobbyProcessingEngine]

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param msg a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  override def process(box: BOX, msg: String): Option[JsValue] = {

    val jsValue: JsValue = Json.parse(msg)

    def log(label: String): Unit = {
      val format: String = "process %s%n"
      logger.info(format.format(label))
    }

    flowController.flow(jsValue) match {
      case Some(pong: JsonPong) =>
        log("JsonPong")
        pongEngine.flatMap(_.process(box, pong))
      case Some(ping: JsonPing) =>
        log("JsonPing")
        pingEngine.flatMap(_.process(box, ping))
      case Some(waitingPage: JsonWaitingPage) =>
        log("JsonWaitingPage")
        waitingPageEngine.flatMap(_.process(box, waitingPage))
      case Some(lobby: JsonLobby) =>
        log("JsonLobby")
        lobbyEngine.flatMap(_.process(box, lobby))
      case Some(enterLobby: JsonEnterLobby) =>
        log("JsonEnterLobby")
        enterLobbyEngine.flatMap(_.process(box, enterLobby))
      case Some(getAvatarInfo: JsonGetAvatarInfo) =>
        log("JsonGetAvatarInfo")
        getAvatarInfoEngine.flatMap(_.process(box, getAvatarInfo))
      case Some(avatarInfo: JsonAvatarInfo) =>
        log("JsonAvatarInfo")
        avatarInfoEngine.flatMap(_.process(box, avatarInfo))
      case Some(selectVillage: JsonSelectVillage) =>
        log("JsonSelectVillage")
        selectVillageEngine.flatMap(_.process(box, selectVillage))
      case Some(leaveWaitingPage: JsonLeaveWaitingPage) =>
        log("JsonLeaveWaitingPage")
        leaveWaitingPageEngine.flatMap(_.process(box, leaveWaitingPage))
      case Some(kickOutPlayer: JsonKickOutPlayer) =>
        log("JsonKickOutPlayer")
        kickOutPlayerEngine.flatMap(_.process(box, kickOutPlayer))
      case Some(buildVillage: JsonBuildVillage) =>
        log("JsonBuildVillage")
        buildVillageEngine.flatMap(_.process(box, buildVillage))
      case Some(advancedSearch: JsonAdvancedSearch) =>
        log("JsonAdvancedSearch")
        advancedSearchEngine.flatMap(_.process(box, advancedSearch))
      case Some(idSearch: JsonIdSearch) =>
        log("JsonIdSearch")
        idSearchEngine.flatMap(_.process(box, idSearch))
      case Some(play: JsonPlay) =>
        log("JsonPlay")
        playEngine.flatMap(_.process(box, play))
      case Some(played: JsonPlayed) =>
        log("JsonPlayed")
        playedEngine.flatMap(_.process(box, played))
      case Some(playedWithToken: JsonPlayedWithToken) =>
        log("JsonPlayedWithToken")
        playedWithTokenEngine.flatMap(_.process(box, playedWithToken))
      case Some(ready: JsonReady) =>
        log("JsonReady")
        readyEngine.flatMap(_.process(box, ready))
      case Some(searchResult: JsonSearchResult) =>
        log("JsonSearchResult")
        searchResultEngine.flatMap(_.process(box, searchResult))
      case Some(changeLang: JsonChangeLang) =>
        log("JsonChangeLang")
        changeLangEngine.flatMap(_.process(box, changeLang))
      case Some(changeUserEmail: JsonChangeUserEmail) =>
        log("JsonChangeUserEmail")
        changeUserEmailEngine.flatMap(_.process(box, changeUserEmail))
      case Some(changeUserName: JsonChangeUserName) =>
        log("JsonChangeUserName")
        changeUserNameEngine.flatMap(_.process(box, changeUserName))
      case Some(changeUserPassword: JsonChangeUserPassword) =>
        log("JsonChangeUserPassword")
        changeUserPasswordEngine.flatMap(_.process(box, changeUserPassword))
      case Some(getSettings: JsonGetSettings) =>
        log("JsonGetSettings")
        getSettingsEngine.flatMap(_.process(box, getSettings))
      case Some(settings: JsonSettings) =>
        log("JsonSettings")
        settingsEngine.flatMap(_.process(box, settings))
      case _ =>
        None
    }
  }
}