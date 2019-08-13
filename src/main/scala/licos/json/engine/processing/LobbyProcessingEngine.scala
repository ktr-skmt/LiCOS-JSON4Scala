package licos.json.engine.processing

import licos.json.engine.BOX
import licos.json.engine.analysis.lobby._
import licos.json.flow.LobbyFlowController
import licos.json.lobby._
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
class LobbyProcessingEngine(pongEngine: PongAnalysisEngine,
                            pingEngine: PingAnalysisEngine,
                            waitingPageEngine: WaitingPageAnalysisEngine,
                            lobbyEngine: LobbyAnalysisEngine,
                            enterLobbyEngine: EnterLobbyAnalysisEngine,
                            getAvatarInfoEngine: GetAvatarInfoAnalysisEngine,
                            avatarInfoEngine: AvatarInfoAnalysisEngine,
                            selectVillageEngine: SelectVillageAnalysisEngine,
                            leaveWaitingPageEngine: LeaveWaitingPageAnalysisEngine,
                            kickOutPlayerEngine: KickOutPlayerAnalysisEngine,
                            buildVillageEngine: BuildVillageAnalysisEngine,
                            advancedSearchEngine: AdvancedSearchAnalysisEngine,
                            idSearchEngine: IdSearchAnalysisEngine,
                            playEngine: PlayAnalysisEngine,
                            playedWithTokenEngine: PlayedWithTokenAnalysisEngine,
                            readyEngine: ReadyAnalysisEngine,
                            searchResultEngine: SearchResultAnalysisEngine,
                            changeLangEngine: ChangeLangAnalysisEngine,
                            changeUserEmailEngine: ChangeUserEmailAnalysisEngine,
                            changeUserNameEngine: ChangeUserNameAnalysisEngine,
                            changeUserPasswordEngine: ChangeUserPasswordAnalysisEngine,
                            getSettingsEngine: GetSettingsAnalysisEngine,
                            settingsEngine: SettingsAnalysisEngine) extends ProcessingEngine {

  override protected val flowController = new LobbyFlowController()

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param msg a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  override def process(box: BOX, msg: String): Option[JsValue] = {

    val jsValue: JsValue = Json.parse(msg)

    def log(label: String): Unit = {
      val format: String = "LobbyProcessingEngine.process %s%n"
      System.err.printf(format, label)
    }

    flowController.flow(jsValue) match {
      case Some(pong: JsonPong) =>
        log("JsonPong")
        pongEngine.process(box, pong)
      case Some(ping: JsonPing) =>
        log("JsonPing")
        pingEngine.process(box, ping)
      case Some(waitingPage: JsonWaitingPage) =>
        log("JsonWaitingPage")
        waitingPageEngine.process(box, waitingPage)
      case Some(lobby: JsonLobby) =>
        log("JsonLobby")
        lobbyEngine.process(box, lobby)
      case Some(enterLobby: JsonEnterLobby) =>
        log("JsonEnterLobby")
        enterLobbyEngine.process(box, enterLobby)
      case Some(getAvatarInfo: JsonGetAvatarInfo) =>
        log("JsonGetAvatarInfo")
        getAvatarInfoEngine.process(box, getAvatarInfo)
      case Some(avatarInfo: JsonAvatarInfo) =>
        log("JsonAvatarInfo")
        avatarInfoEngine.process(box, avatarInfo)
      case Some(selectVillage: JsonSelectVillage) =>
        log("JsonSelectVillage")
        selectVillageEngine.process(box, selectVillage)
      case Some(leaveWaitingPage: JsonLeaveWaitingPage) =>
        log("JsonLeaveWaitingPage")
        leaveWaitingPageEngine.process(box, leaveWaitingPage)
      case Some(kickOutPlayer: JsonKickOutPlayer) =>
        log("JsonKickOutPlayer")
        kickOutPlayerEngine.process(box, kickOutPlayer)
      case Some(buildVillage: JsonBuildVillage) =>
        log("JsonBuildVillage")
        buildVillageEngine.process(box, buildVillage)
      case Some(advancedSearch: JsonAdvancedSearch) =>
        log("JsonAdvancedSearch")
        advancedSearchEngine.process(box, advancedSearch)
      case Some(idSearch: JsonIdSearch) =>
        log("JsonIdSearch")
        idSearchEngine.process(box, idSearch)
      case Some(play: JsonPlay) =>
        log("JsonPlay")
        playEngine.process(box, play)
      case Some(playedWithToken: JsonPlayedWithToken) =>
        log("JsonPlayedWithToken")
        playedWithTokenEngine.process(box, playedWithToken)
      case Some(ready: JsonReady) =>
        log("JsonReady")
        readyEngine.process(box, ready)
      case Some(searchResult: JsonSearchResult) =>
        log("JsonSearchResult")
        searchResultEngine.process(box, searchResult)
      case Some(changeLang: JsonChangeLang) =>
        log("JsonChangeLang")
        changeLangEngine.process(box, changeLang)
      case Some(changeUserEmail: JsonChangeUserEmail) =>
        log("JsonChangeUserEmail")
        changeUserEmailEngine.process(box, changeUserEmail)
      case Some(changeUserName: JsonChangeUserName) =>
        log("JsonChangeUserName")
        changeUserNameEngine.process(box, changeUserName)
      case Some(changeUserPassword: JsonChangeUserPassword) =>
        log("JsonChangeUserPassword")
        changeUserPasswordEngine.process(box, changeUserPassword)
      case Some(getSettings: JsonGetSettings) =>
        log("JsonGetSettings")
        getSettingsEngine.process(box, getSettings)
      case Some(settings: JsonSettings) =>
        log("JsonSettings")
        settingsEngine.process(box, settings)
      case _ =>
        None
    }
  }
}
