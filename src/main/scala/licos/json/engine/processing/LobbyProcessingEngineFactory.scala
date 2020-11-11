package licos.json.engine.processing

import licos.json.engine.analysis.lobby.client2server._
import licos.json.engine.analysis.lobby.server2client._
import licos.json.engine.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine

/** Lobby processing engine factory.
  */
@SuppressWarnings(Array[String]("org.wartremover.warts.Var", "org.wartremover.warts.Overloading"))
final class LobbyProcessingEngineFactory extends ProcessingEngineFactory {

  private var pongEngine:          Option[PongAnalysisEngine]          = Option.empty[PongAnalysisEngine]
  private var pingEngine:          Option[PingAnalysisEngine]          = Option.empty[PingAnalysisEngine]
  private var waitingPageEngine:   Option[WaitingPageAnalysisEngine]   = Option.empty[WaitingPageAnalysisEngine]
  private var lobbyEngine:         Option[LobbyAnalysisEngine]         = Option.empty[LobbyAnalysisEngine]
  private var enterLobbyEngine:    Option[EnterLobbyAnalysisEngine]    = Option.empty[EnterLobbyAnalysisEngine]
  private var getAvatarInfoEngine: Option[GetAvatarInfoAnalysisEngine] = Option.empty[GetAvatarInfoAnalysisEngine]
  private var avatarInfoEngine:    Option[AvatarInfoAnalysisEngine]    = Option.empty[AvatarInfoAnalysisEngine]
  private var selectVillageEngine: Option[SelectVillageAnalysisEngine] = Option.empty[SelectVillageAnalysisEngine]
  private var leaveWaitingPageEngine: Option[LeaveWaitingPageAnalysisEngine] =
    Option.empty[LeaveWaitingPageAnalysisEngine]
  private var kickOutPlayerEngine:   Option[KickOutPlayerAnalysisEngine]   = Option.empty[KickOutPlayerAnalysisEngine]
  private var buildVillageEngine:    Option[BuildVillageAnalysisEngine]    = Option.empty[BuildVillageAnalysisEngine]
  private var advancedSearchEngine:  Option[AdvancedSearchAnalysisEngine]  = Option.empty[AdvancedSearchAnalysisEngine]
  private var idSearchEngine:        Option[IdSearchAnalysisEngine]        = Option.empty[IdSearchAnalysisEngine]
  private var playEngine:            Option[PlayAnalysisEngine]            = Option.empty[PlayAnalysisEngine]
  private var playedEngine:          Option[PlayedAnalysisEngine]          = Option.empty[PlayedAnalysisEngine]
  private var playedWithTokenEngine: Option[PlayedWithTokenAnalysisEngine] = Option.empty[PlayedWithTokenAnalysisEngine]
  private var readyEngine:           Option[ReadyAnalysisEngine]           = Option.empty[ReadyAnalysisEngine]
  private var searchResultEngine:    Option[SearchResultAnalysisEngine]    = Option.empty[SearchResultAnalysisEngine]
  private var changeLanguageEngine:  Option[ChangeLanguageAnalysisEngine]  = Option.empty[ChangeLanguageAnalysisEngine]
  private var changeUserEmailEngine: Option[ChangeUserEmailAnalysisEngine] = Option.empty[ChangeUserEmailAnalysisEngine]
  private var changeUserNameEngine:  Option[ChangeUserNameAnalysisEngine]  = Option.empty[ChangeUserNameAnalysisEngine]
  private var changeUserPasswordEngine: Option[ChangeUserPasswordAnalysisEngine] =
    Option.empty[ChangeUserPasswordAnalysisEngine]
  private var getSettingsEngine: Option[GetSettingsAnalysisEngine] = Option.empty[GetSettingsAnalysisEngine]
  private var settingsEngine:    Option[SettingsAnalysisEngine]    = Option.empty[SettingsAnalysisEngine]
  private var authorizationRequestEngine: Option[AuthorizationRequestAnalysisEngine] =
    Option.empty[AuthorizationRequestAnalysisEngine]
  private var authorizationRequestAcceptedResponseEngine: Option[AuthorizationRequestAcceptedResponseAnalysisEngine] =
    Option.empty[AuthorizationRequestAcceptedResponseAnalysisEngine]
  private var authorizationRequestAcceptedEngine: Option[AuthorizationRequestAcceptedAnalysisEngine] =
    Option.empty[AuthorizationRequestAcceptedAnalysisEngine]
  private var renewAvatarTokenEngine: Option[RenewAvatarTokenAnalysisEngine] =
    Option.empty[RenewAvatarTokenAnalysisEngine]
  private var createHumanPlayerEngine: Option[CreateHumanPlayerAnalysisEngine] =
    Option.empty[CreateHumanPlayerAnalysisEngine]
  private var createOnymousAudienceEngine: Option[CreateOnymousAudienceAnalysisEngine] =
    Option.empty[CreateOnymousAudienceAnalysisEngine]
  private var createRobotPlayerEngine: Option[CreateRobotPlayerAnalysisEngine] =
    Option.empty[CreateRobotPlayerAnalysisEngine]
  private var deleteAvatarEngine: Option[DeleteAvatarAnalysisEngine] = Option.empty[DeleteAvatarAnalysisEngine]
  private var runRobotPlayerInTheBackgroundEngine: Option[RunRobotPlayerInTheBackgroundAnalysisEngine] =
    Option.empty[RunRobotPlayerInTheBackgroundAnalysisEngine]
  private var stopRobotPlayerEngine: Option[StopRobotPlayerAnalysisEngine] = Option.empty[StopRobotPlayerAnalysisEngine]
  private var humanPlayerSelectionPageEngine: Option[HumanPlayerSelectionPageAnalysisEngine] =
    Option.empty[HumanPlayerSelectionPageAnalysisEngine]
  private var onymousAudienceSelectionPageEngine: Option[OnymousAudienceSelectionPageAnalysisEngine] =
    Option.empty[OnymousAudienceSelectionPageAnalysisEngine]
  private var robotPlayerSelectionPageEngine: Option[RobotPlayerSelectionPageAnalysisEngine] =
    Option.empty[RobotPlayerSelectionPageAnalysisEngine]
  private var changeAvatarEngine: Option[ChangeAvatarAnalysisEngine] = Option.empty[ChangeAvatarAnalysisEngine]
  private var enterAvatarSelectionPageEngine: Option[EnterAvatarSelectionPageAnalysisEngine] =
    Option.empty[EnterAvatarSelectionPageAnalysisEngine]

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
      changeAvatarEngine:                         Option[ChangeAvatarAnalysisEngine],
      enterAvatarSelectionPageEngine:             Option[EnterAvatarSelectionPageAnalysisEngine]
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

  def set(changeLanguageEngine: ChangeLanguageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeLanguageEngine = Option(changeLanguageEngine)
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

  def set(changeAvatarAnalysisEngine: ChangeAvatarAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeAvatarEngine = Option(changeAvatarAnalysisEngine)
    this
  }

  def set(
      enterAvatarSelectionPageAnalysisEngine: EnterAvatarSelectionPageAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.enterAvatarSelectionPageEngine = Option(enterAvatarSelectionPageAnalysisEngine)
    this
  }
}
