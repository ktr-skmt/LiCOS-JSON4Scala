package licos.json.engine.processing

import licos.json.engine.analysis.lobby.client2server._
import licos.json.engine.analysis.lobby.server2client._
import licos.json.engine.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine

/** Lobby processing engine factory.
  */
@SuppressWarnings(Array[String]("org.wartremover.warts.Var", "org.wartremover.warts.Overloading"))
final class LobbyProcessingEngineFactory extends ProcessingEngineFactory {

  private var pongEngine:                 Option[PongAnalysisEngine]                 = None
  private var pingEngine:                 Option[PingAnalysisEngine]                 = None
  private var waitingPageEngine:          Option[WaitingPageAnalysisEngine]          = None
  private var lobbyEngine:                Option[LobbyAnalysisEngine]                = None
  private var enterLobbyEngine:           Option[EnterLobbyAnalysisEngine]           = None
  private var getAvatarInfoEngine:        Option[GetAvatarInfoAnalysisEngine]        = None
  private var avatarInfoEngine:           Option[AvatarInfoAnalysisEngine]           = None
  private var selectVillageEngine:        Option[SelectVillageAnalysisEngine]        = None
  private var leaveWaitingPageEngine:     Option[LeaveWaitingPageAnalysisEngine]     = None
  private var kickOutPlayerEngine:        Option[KickOutPlayerAnalysisEngine]        = None
  private var buildVillageEngine:         Option[BuildVillageAnalysisEngine]         = None
  private var advancedSearchEngine:       Option[AdvancedSearchAnalysisEngine]       = None
  private var idSearchEngine:             Option[IdSearchAnalysisEngine]             = None
  private var playEngine:                 Option[PlayAnalysisEngine]                 = None
  private var playedEngine:               Option[PlayedAnalysisEngine]               = None
  private var playedWithTokenEngine:      Option[PlayedWithTokenAnalysisEngine]      = None
  private var readyEngine:                Option[ReadyAnalysisEngine]                = None
  private var searchResultEngine:         Option[SearchResultAnalysisEngine]         = None
  private var changeLangEngine:           Option[ChangeLangAnalysisEngine]           = None
  private var changeUserEmailEngine:      Option[ChangeUserEmailAnalysisEngine]      = None
  private var changeUserNameEngine:       Option[ChangeUserNameAnalysisEngine]       = None
  private var changeUserPasswordEngine:   Option[ChangeUserPasswordAnalysisEngine]   = None
  private var getSettingsEngine:          Option[GetSettingsAnalysisEngine]          = None
  private var settingsEngine:             Option[SettingsAnalysisEngine]             = None
  private var authorizationRequestEngine: Option[AuthorizationRequestAnalysisEngine] = None
  private var authorizationRequestAcceptedResponseEngine: Option[AuthorizationRequestAcceptedResponseAnalysisEngine] =
    None
  private var authorizationRequestAcceptedEngine:  Option[AuthorizationRequestAcceptedAnalysisEngine]  = None
  private var renewAvatarTokenEngine:              Option[RenewAvatarTokenAnalysisEngine]              = None
  private var newAvatarTokenEngine:                Option[NewAvatarTokenAnalysisEngine]                = None
  private var createHumanPlayerEngine:             Option[CreateHumanPlayerAnalysisEngine]             = None
  private var createOnymousAudienceEngine:         Option[CreateOnymousAudienceAnalysisEngine]         = None
  private var createRobotPlayerEngine:             Option[CreateRobotPlayerAnalysisEngine]             = None
  private var deleteAvatarEngine:                  Option[DeleteAvatarAnalysisEngine]                  = None
  private var runRobotPlayerInTheBackgroundEngine: Option[RunRobotPlayerInTheBackgroundAnalysisEngine] = None
  private var runRobotPlayerInTheForegroundEngine: Option[RunRobotPlayerInTheForegroundAnalysisEngine] = None
  private var selectHumanPlayerEngine:             Option[SelectHumanPlayerAnalysisEngine]             = None
  private var selectOnymousAudienceEngine:         Option[SelectOnymousAudienceAnalysisEngine]         = None
  private var stopRobotPlayerEngine:               Option[StopRobotPlayerAnalysisEngine]               = None
  private var humanPlayerSelectionPageEngine:      Option[HumanPlayerSelectionPageAnalysisEngine]      = None
  private var onymousAudienceSelectionPageEngine:  Option[OnymousAudienceSelectionPageAnalysisEngine]  = None
  private var robotPlayerSelectionPageEngine:      Option[RobotPlayerSelectionPageAnalysisEngine]      = None

  /** Creates a lobby processing engine.
    *
    * @return a processing engine.
    */
  override def create: LobbyProcessingEngine = {
    new LobbyProcessingEngine(
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
      renewAvatarTokenEngine:                     Option[RenewAvatarTokenAnalysisEngine],
      newAvatarTokenEngine:                       Option[NewAvatarTokenAnalysisEngine],
      createHumanPlayerEngine:                    Option[CreateHumanPlayerAnalysisEngine],
      createOnymousAudienceEngine:                Option[CreateOnymousAudienceAnalysisEngine],
      createRobotPlayerEngine:                    Option[CreateRobotPlayerAnalysisEngine],
      deleteAvatarEngine:                         Option[DeleteAvatarAnalysisEngine],
      runRobotPlayerInTheBackgroundEngine:        Option[RunRobotPlayerInTheBackgroundAnalysisEngine],
      runRobotPlayerInTheForegroundEngine:        Option[RunRobotPlayerInTheForegroundAnalysisEngine],
      selectHumanPlayerEngine:                    Option[SelectHumanPlayerAnalysisEngine],
      selectOnymousAudienceEngine:                Option[SelectOnymousAudienceAnalysisEngine],
      stopRobotPlayerEngine:                      Option[StopRobotPlayerAnalysisEngine],
      humanPlayerSelectionPageEngine:             Option[HumanPlayerSelectionPageAnalysisEngine],
      onymousAudienceSelectionPageEngine:         Option[OnymousAudienceSelectionPageAnalysisEngine],
      robotPlayerSelectionPageEngine:             Option[RobotPlayerSelectionPageAnalysisEngine]
    )
  }

  def set(pongEngine: PongAnalysisEngine): LobbyProcessingEngineFactory = {
    this.pongEngine = Option(pongEngine)
    this
  }

  def set(pingEngine: PingAnalysisEngine): LobbyProcessingEngineFactory = {
    this.pingEngine = Option(pingEngine)
    this
  }

  def set(waitingPageEngine: WaitingPageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.waitingPageEngine = Option(waitingPageEngine)
    this
  }

  def set(lobbyEngine: LobbyAnalysisEngine): LobbyProcessingEngineFactory = {
    this.lobbyEngine = Option(lobbyEngine)
    this
  }

  def set(enterLobbyEngine: EnterLobbyAnalysisEngine): LobbyProcessingEngineFactory = {
    this.enterLobbyEngine = Option(enterLobbyEngine)
    this
  }

  def set(getAvatarInfoEngine: GetAvatarInfoAnalysisEngine): LobbyProcessingEngineFactory = {
    this.getAvatarInfoEngine = Option(getAvatarInfoEngine)
    this
  }

  def set(avatarInfoEngine: AvatarInfoAnalysisEngine): LobbyProcessingEngineFactory = {
    this.avatarInfoEngine = Option(avatarInfoEngine)
    this
  }

  def set(selectVillageEngine: SelectVillageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.selectVillageEngine = Option(selectVillageEngine)
    this
  }

  def set(leaveWaitingPageEngine: LeaveWaitingPageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.leaveWaitingPageEngine = Option(leaveWaitingPageEngine)
    this
  }

  def set(kickOutPlayerEngine: KickOutPlayerAnalysisEngine): LobbyProcessingEngineFactory = {
    this.kickOutPlayerEngine = Option(kickOutPlayerEngine)
    this
  }

  def set(buildVillageEngine: BuildVillageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.buildVillageEngine = Option(buildVillageEngine)
    this
  }

  def set(advancedSearchEngine: AdvancedSearchAnalysisEngine): LobbyProcessingEngineFactory = {
    this.advancedSearchEngine = Option(advancedSearchEngine)
    this
  }

  def set(idSearchEngine: IdSearchAnalysisEngine): LobbyProcessingEngineFactory = {
    this.idSearchEngine = Option(idSearchEngine)
    this
  }

  def set(playEngine: PlayAnalysisEngine): LobbyProcessingEngineFactory = {
    this.playEngine = Option(playEngine)
    this
  }

  def set(playedEngine: PlayedAnalysisEngine): LobbyProcessingEngineFactory = {
    this.playedEngine = Option(playedEngine)
    this
  }

  def set(playedWithTokenEngine: PlayedWithTokenAnalysisEngine): LobbyProcessingEngineFactory = {
    this.playedWithTokenEngine = Option(playedWithTokenEngine)
    this
  }

  def set(readyEngine: ReadyAnalysisEngine): LobbyProcessingEngineFactory = {
    this.readyEngine = Option(readyEngine)
    this
  }

  def set(searchResultEngine: SearchResultAnalysisEngine): LobbyProcessingEngineFactory = {
    this.searchResultEngine = Option(searchResultEngine)
    this
  }

  def set(changeLangEngine: ChangeLangAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeLangEngine = Option(changeLangEngine)
    this
  }

  def set(changeUserEmailEngine: ChangeUserEmailAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeUserEmailEngine = Option(changeUserEmailEngine)
    this
  }

  def set(changeUserNameEngine: ChangeUserNameAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeUserNameEngine = Option(changeUserNameEngine)
    this
  }

  def set(changeUserPasswordEngine: ChangeUserPasswordAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeUserPasswordEngine = Option(changeUserPasswordEngine)
    this
  }

  def set(getSettingsEngine: GetSettingsAnalysisEngine): LobbyProcessingEngineFactory = {
    this.getSettingsEngine = Option(getSettingsEngine)
    this
  }

  def set(settingsEngine: SettingsAnalysisEngine): LobbyProcessingEngineFactory = {
    this.settingsEngine = Option(settingsEngine)
    this
  }

  def set(authorizationRequestEngine: AuthorizationRequestAnalysisEngine): LobbyProcessingEngineFactory = {
    this.authorizationRequestEngine = Option(authorizationRequestEngine)
    this
  }

  def set(
      authorizationRequestAcceptedResponseEngine: AuthorizationRequestAcceptedResponseAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.authorizationRequestAcceptedResponseEngine = Option(authorizationRequestAcceptedResponseEngine)
    this
  }

  def set(
      authorizationRequestAcceptedEngine: AuthorizationRequestAcceptedAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.authorizationRequestAcceptedEngine = Option(authorizationRequestAcceptedEngine)
    this
  }

  def set(renewAvatarTokenEngine: RenewAvatarTokenAnalysisEngine): LobbyProcessingEngineFactory = {
    this.renewAvatarTokenEngine = Option(renewAvatarTokenEngine)
    this
  }

  def set(newAvatarTokenEngine: NewAvatarTokenAnalysisEngine): LobbyProcessingEngineFactory = {
    this.newAvatarTokenEngine = Option(newAvatarTokenEngine)
    this
  }

  def set(createHumanPlayerEngine: CreateHumanPlayerAnalysisEngine): LobbyProcessingEngineFactory = {
    this.createHumanPlayerEngine = Option(createHumanPlayerEngine)
    this
  }

  def set(createOnymousAudienceEngine: CreateOnymousAudienceAnalysisEngine): LobbyProcessingEngineFactory = {
    this.createOnymousAudienceEngine = Option(createOnymousAudienceEngine)
    this
  }

  def set(createRobotPlayerEngine: CreateRobotPlayerAnalysisEngine): LobbyProcessingEngineFactory = {
    this.createRobotPlayerEngine = Option(createRobotPlayerEngine)
    this
  }

  def set(deleteAvatarEngine: DeleteAvatarAnalysisEngine): LobbyProcessingEngineFactory = {
    this.deleteAvatarEngine = Option(deleteAvatarEngine)
    this
  }

  def set(
      runRobotPlayerInTheBackgroundEngine: RunRobotPlayerInTheBackgroundAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.runRobotPlayerInTheBackgroundEngine = Option(runRobotPlayerInTheBackgroundEngine)
    this
  }

  def set(
      runRobotPlayerInTheForegroundEngine: RunRobotPlayerInTheForegroundAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.runRobotPlayerInTheForegroundEngine = Option(runRobotPlayerInTheForegroundEngine)
    this
  }

  def set(selectHumanPlayerEngine: SelectHumanPlayerAnalysisEngine): LobbyProcessingEngineFactory = {
    this.selectHumanPlayerEngine = Option(selectHumanPlayerEngine)
    this
  }

  def set(selectOnymousAudienceEngine: SelectOnymousAudienceAnalysisEngine): LobbyProcessingEngineFactory = {
    this.selectOnymousAudienceEngine = Option(selectOnymousAudienceEngine)
    this
  }

  def set(stopRobotPlayerEngine: StopRobotPlayerAnalysisEngine): LobbyProcessingEngineFactory = {
    this.stopRobotPlayerEngine = Option(stopRobotPlayerEngine)
    this
  }

  def set(
      humanPlayerSelectionPageAnalysisEngine: HumanPlayerSelectionPageAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.humanPlayerSelectionPageEngine = Option(humanPlayerSelectionPageAnalysisEngine)
    this
  }

  def set(
      onymousAudienceSelectionPageAnalysisEngine: OnymousAudienceSelectionPageAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.onymousAudienceSelectionPageEngine = Option(onymousAudienceSelectionPageAnalysisEngine)
    this
  }

  def set(
      robotPlayerSelectionPageAnalysisEngine: RobotPlayerSelectionPageAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.robotPlayerSelectionPageEngine = Option(robotPlayerSelectionPageAnalysisEngine)
    this
  }
}
