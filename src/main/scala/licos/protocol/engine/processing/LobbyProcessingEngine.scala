package licos.protocol.engine.processing

import com.typesafe.scalalogging.Logger
import licos.json.element.lobby.client2server.{
  JsonAdvancedSearch,
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
  JsonSelectVillage
}
import licos.json.element.lobby.server2client.{
  JsonAvatarInfo,
  JsonGetAvatarInfo,
  JsonLobby,
  JsonPing,
  JsonPlayed,
  JsonSearchResult,
  JsonSettings,
  JsonWaitingPage
}
import licos.json.element.lobby.server2server.JsonPlayedWithToken
import licos.json.flow.{FlowController, LobbyFlowController}
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.{
  AdvancedSearchProtocol,
  BuildVillageProtocol,
  ChangeLangProtocol,
  ChangeUserEmailProtocol,
  ChangeUserNameProtocol,
  ChangeUserPasswordProtocol,
  EnterLobbyProtocol,
  GetAvatarInfoProtocol,
  GetSettingsProtocol,
  IdSearchProtocol,
  KickOutPlayerProtocol,
  LeaveWaitingPageProtocol,
  PlayProtocol,
  PongProtocol,
  ReadyProtocol,
  SelectVillageProtocol
}
import licos.protocol.element.lobby.server2client.{
  AvatarInfoProtocol,
  LobbyProtocol,
  PingProtocol,
  PlayedProtocol,
  SearchResultProtocol,
  SettingsProtocol,
  WaitingPageProtocol
}
import licos.protocol.element.lobby.server2server.PlayedWithTokenProtocol
import licos.protocol.engine.analysis.lobby.client2server.{
  AdvancedSearchAnalysisEngine,
  BuildVillageAnalysisEngine,
  ChangeLangAnalysisEngine,
  ChangeUserEmailAnalysisEngine,
  ChangeUserNameAnalysisEngine,
  ChangeUserPasswordAnalysisEngine,
  EnterLobbyAnalysisEngine,
  GetAvatarInfoAnalysisEngine,
  GetSettingsAnalysisEngine,
  IdSearchAnalysisEngine,
  KickOutPlayerAnalysisEngine,
  LeaveWaitingPageAnalysisEngine,
  PlayAnalysisEngine,
  PongAnalysisEngine,
  ReadyAnalysisEngine,
  SelectVillageAnalysisEngine
}
import licos.protocol.engine.analysis.lobby.server2client.{
  AvatarInfoAnalysisEngine,
  LobbyAnalysisEngine,
  PingAnalysisEngine,
  PlayedAnalysisEngine,
  SearchResultAnalysisEngine,
  SettingsAnalysisEngine,
  WaitingPageAnalysisEngine
}
import licos.protocol.engine.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine
import play.api.libs.json.{JsValue, Json}

import scala.util.{Failure, Try}

class LobbyProcessingEngine(
    pongEngine:               Option[PongAnalysisEngine],
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
    settingsEngine:           Option[SettingsAnalysisEngine]
) extends ProcessingEngine {
  override protected val flowController: FlowController = new LobbyFlowController()

  private final val logger = Logger[LobbyProcessingEngine]

  def process(box: LobbyBOX, msg: String): Try[LobbyMessageProtocol] = {

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
            PongProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(PongAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(PongAnalysisEngine.name))
        }
      case Right(json: JsonPing) =>
        log("JsonPing")
        pingEngine match {
          case Some(engine) =>
            PingProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(PingAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(PingAnalysisEngine.name))
        }
      case Right(json: JsonWaitingPage) =>
        log("JsonWaitingPage")
        waitingPageEngine match {
          case Some(engine) =>
            WaitingPageProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(WaitingPageAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(WaitingPageAnalysisEngine.name))
        }
      case Right(json: JsonLobby) =>
        log("JsonLobby")
        lobbyEngine match {
          case Some(engine) =>
            LobbyProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(LobbyAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(LobbyAnalysisEngine.name))
        }
      case Right(json: JsonEnterLobby) =>
        log("JsonEnterLobby")
        enterLobbyEngine match {
          case Some(engine) =>
            EnterLobbyProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(EnterLobbyAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(EnterLobbyAnalysisEngine.name))
        }
      case Right(json: JsonGetAvatarInfo) =>
        log("JsonGetAvatarInfo")
        getAvatarInfoEngine match {
          case Some(engine) =>
            GetAvatarInfoProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(GetAvatarInfoAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(GetAvatarInfoAnalysisEngine.name))
        }
      case Right(json: JsonAvatarInfo) =>
        log("JsonAvatarInfo")
        avatarInfoEngine match {
          case Some(engine) =>
            AvatarInfoProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(AvatarInfoAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(AvatarInfoAnalysisEngine.name))
        }
      case Right(json: JsonSelectVillage) =>
        log("JsonSelectVillage")
        selectVillageEngine match {
          case Some(engine) =>
            SelectVillageProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(SelectVillageAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(SelectVillageAnalysisEngine.name))
        }
      case Right(json: JsonLeaveWaitingPage) =>
        log("JsonLeaveWaitingPage")
        leaveWaitingPageEngine match {
          case Some(engine) =>
            LeaveWaitingPageProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(LeaveWaitingPageAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(LeaveWaitingPageAnalysisEngine.name))
        }
      case Right(json: JsonKickOutPlayer) =>
        log("JsonKickOutPlayer")
        kickOutPlayerEngine match {
          case Some(engine) =>
            KickOutPlayerProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(KickOutPlayerAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(KickOutPlayerAnalysisEngine.name))
        }
      case Right(json: JsonBuildVillage) =>
        log("JsonBuildVillage")
        buildVillageEngine match {
          case Some(engine) =>
            BuildVillageProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(BuildVillageAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(BuildVillageAnalysisEngine.name))
        }
      case Right(json: JsonAdvancedSearch) =>
        log("JsonAdvancedSearch")
        advancedSearchEngine match {
          case Some(engine) =>
            AdvancedSearchProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(AdvancedSearchAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(AdvancedSearchAnalysisEngine.name))
        }
      case Right(json: JsonIdSearch) =>
        log("JsonIdSearch")
        idSearchEngine match {
          case Some(engine) =>
            IdSearchProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(IdSearchAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(IdSearchAnalysisEngine.name))
        }
      case Right(json: JsonPlay) =>
        log("JsonPlay")
        playEngine match {
          case Some(engine) =>
            PlayProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(PlayAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(PlayAnalysisEngine.name))
        }
      case Right(json: JsonPlayed) =>
        log("JsonPlayed")
        playedEngine match {
          case Some(engine) =>
            PlayedProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(PlayedAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(PlayedAnalysisEngine.name))
        }
      case Right(json: JsonPlayedWithToken) =>
        log("JsonPlayedWithToken")
        playedWithTokenEngine match {
          case Some(engine) =>
            PlayedWithTokenProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(PlayedWithTokenAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(PlayedWithTokenAnalysisEngine.name))
        }
      case Right(json: JsonReady) =>
        log("JsonReady")
        readyEngine match {
          case Some(engine) =>
            ReadyProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ReadyAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ReadyAnalysisEngine.name))
        }
      case Right(json: JsonSearchResult) =>
        log("JsonSearchResult")
        searchResultEngine match {
          case Some(engine) =>
            SearchResultProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(SearchResultAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(SearchResultAnalysisEngine.name))
        }
      case Right(json: JsonChangeLang) =>
        log("JsonChangeLang")
        changeLangEngine match {
          case Some(engine) =>
            ChangeLangProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ChangeLangAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ChangeLangAnalysisEngine.name))
        }
      case Right(json: JsonChangeUserEmail) =>
        log("JsonChangeUserEmail")
        changeUserEmailEngine match {
          case Some(engine) =>
            ChangeUserEmailProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ChangeUserEmailAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ChangeUserEmailAnalysisEngine.name))
        }
      case Right(json: JsonChangeUserName) =>
        log("JsonChangeUserName")
        changeUserNameEngine match {
          case Some(engine) =>
            ChangeUserNameProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ChangeUserNameAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ChangeUserNameAnalysisEngine.name))
        }
      case Right(json: JsonChangeUserPassword) =>
        log("JsonChangeUserPassword")
        changeUserPasswordEngine match {
          case Some(engine) =>
            ChangeUserPasswordProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ChangeUserPasswordAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ChangeUserPasswordAnalysisEngine.name))
        }
      case Right(json: JsonGetSettings) =>
        log("JsonGetSettings")
        getSettingsEngine match {
          case Some(engine) =>
            GetSettingsProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(GetSettingsAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(GetSettingsAnalysisEngine.name))
        }
      case Right(json: JsonSettings) =>
        log("JsonSettings")
        settingsEngine match {
          case Some(engine) =>
            SettingsProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(SettingsAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(SettingsAnalysisEngine.name))
        }
      case _ =>
        Failure(new NoEngineException("AnalysisEngine"))
    }
  }
}
