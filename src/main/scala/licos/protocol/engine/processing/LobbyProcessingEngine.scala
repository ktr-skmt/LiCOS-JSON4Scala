package licos.protocol.engine.processing

import com.typesafe.scalalogging.Logger
import licos.json.element.lobby.{JsonAdvancedSearch, JsonAvatarInfo, JsonBuildVillage, JsonChangeLang, JsonChangeUserEmail, JsonChangeUserName, JsonChangeUserPassword, JsonEnterLobby, JsonGetAvatarInfo, JsonGetSettings, JsonIdSearch, JsonKickOutPlayer, JsonLeaveWaitingPage, JsonLobby, JsonPing, JsonPlay, JsonPlayed, JsonPlayedWithToken, JsonPong, JsonReady, JsonSearchResult, JsonSelectVillage, JsonSettings, JsonWaitingPage}
import licos.json.flow.{FlowController, LobbyFlowController}
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.engine.BOX
import licos.protocol.engine.analysis.lobby.client2server.{AdvancedSearchAnalysisEngine, BuildVillageAnalysisEngine, ChangeLangAnalysisEngine, ChangeUserEmailAnalysisEngine, ChangeUserNameAnalysisEngine, ChangeUserPasswordAnalysisEngine, EnterLobbyAnalysisEngine, GetAvatarInfoAnalysisEngine, GetSettingsAnalysisEngine, IdSearchAnalysisEngine, KickOutPlayerAnalysisEngine, LeaveWaitingPageAnalysisEngine, PlayAnalysisEngine, PongAnalysisEngine, ReadyAnalysisEngine, SelectVillageAnalysisEngine}
import licos.protocol.engine.analysis.lobby.server2client.{AvatarInfoAnalysisEngine, LobbyAnalysisEngine, PingAnalysisEngine, PlayedAnalysisEngine, SearchResultAnalysisEngine, SettingsAnalysisEngine, WaitingPageAnalysisEngine}
import licos.protocol.engine.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine
import play.api.libs.json.{JsValue, Json}

import scala.util.Try

class LobbyProcessingEngine(pongEngine:               Option[PongAnalysisEngine],
                            pingEngine:               Option[PingAnalysisEngine],
                            waitingPageEngine:        Option[WaitingPageAnalysisEngine],
                            lobbyEngine:              Option[LobbyAnalysisEngine],
                            enterLobbyEngine:         Option[EnterLobbyAnalysisEngine],
                            getAvatarInfoEngine:      Option[GetAvatarInfoAnalysisEngine],
                            avatarInfoEngine:         Option[AvatarInfoAnalysisEngine],
                            selectVillageEngine:      Option[SelectVillageAnalysisEngine],
                            leaveWaitingPageEngine:   Option[LeaveWaitingPageAnalysisEngine],
                            kickOutPlayerEngine:      Option[KickOutPlayerAnalysisEngine],
                            buildVillageEngine:       Option[BuildVillageAnalysisEngine],
                            advancedSearchEngine:     Option[AdvancedSearchAnalysisEngine],
                            idSearchEngine:           Option[IdSearchAnalysisEngine],
                            playEngine:               Option[PlayAnalysisEngine],
                            playedEngine:             Option[PlayedAnalysisEngine],
                            playedWithTokenEngine:    Option[PlayedWithTokenAnalysisEngine],
                            readyEngine:              Option[ReadyAnalysisEngine],
                            searchResultEngine:       Option[SearchResultAnalysisEngine],
                            changeLangEngine:         Option[ChangeLangAnalysisEngine],
                            changeUserEmailEngine:    Option[ChangeUserEmailAnalysisEngine],
                            changeUserNameEngine:     Option[ChangeUserNameAnalysisEngine],
                            changeUserPasswordEngine: Option[ChangeUserPasswordAnalysisEngine],
                            getSettingsEngine:        Option[GetSettingsAnalysisEngine],
                            settingsEngine:           Option[SettingsAnalysisEngine])
    extends ProcessingEngine {
  override protected val flowController: FlowController = new LobbyFlowController()

  private final val logger = Logger[LobbyProcessingEngine]

  override def process(box: BOX, msg: String): Try[LobbyMessageProtocol] = {

    val jsValue: JsValue = Json.parse(msg)

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    flowController.flow(jsValue) match {
      case Right(json: JsonPong) =>
        log("JsonPong")
        pongEngine match {
          case Some(engine: PongAnalysisEngine) =>
            engine.process(box, json)
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
      case _ =>
        log("return nothing")
        otherwise
    }
  }
}
