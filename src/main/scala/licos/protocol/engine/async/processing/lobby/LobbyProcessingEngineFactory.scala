package licos.protocol.engine.async.processing.lobby

import licos.protocol.engine.async.analysis.lobby.client2server._
import licos.protocol.engine.async.analysis.lobby.server2client._
import licos.protocol.engine.async.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine
import licos.protocol.engine.async.processing.ProcessingEngineFactory

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading", "org.wartremover.warts.Var"))
final class LobbyProcessingEngineFactory extends ProcessingEngineFactory {
  private var pongAnalysisEngine:        Option[PongAnalysisEngine]        = Option.empty[PongAnalysisEngine]
  private var pingAnalysisEngine:        Option[PingAnalysisEngine]        = Option.empty[PingAnalysisEngine]
  private var waitingPageAnalysisEngine: Option[WaitingPageAnalysisEngine] = Option.empty[WaitingPageAnalysisEngine]
  private var lobbyAnalysisEngine:       Option[LobbyAnalysisEngine]       = Option.empty[LobbyAnalysisEngine]
  private var enterLobbyAnalysisEngine:  Option[EnterLobbyAnalysisEngine]  = Option.empty[EnterLobbyAnalysisEngine]
  private var getAvatarInfoAnalysisEngine: Option[GetAvatarInfoAnalysisEngine] =
    Option.empty[GetAvatarInfoAnalysisEngine]
  private var avatarInfoAnalysisEngine: Option[AvatarInfoAnalysisEngine] = Option.empty[AvatarInfoAnalysisEngine]
  private var selectVillageAnalysisEngine: Option[SelectVillageAnalysisEngine] =
    Option.empty[SelectVillageAnalysisEngine]
  private var leaveWaitingPageAnalysisEngine: Option[LeaveWaitingPageAnalysisEngine] =
    Option.empty[LeaveWaitingPageAnalysisEngine]
  private var kickOutPlayerAnalysisEngine: Option[KickOutPlayerAnalysisEngine] =
    Option.empty[KickOutPlayerAnalysisEngine]
  private var buildVillageAnalysisEngine: Option[BuildVillageAnalysisEngine] = Option.empty[BuildVillageAnalysisEngine]
  private var advancedSearchAnalysisEngine: Option[AdvancedSearchAnalysisEngine] =
    Option.empty[AdvancedSearchAnalysisEngine]
  private var idSearchAnalysisEngine: Option[IdSearchAnalysisEngine] = Option.empty[IdSearchAnalysisEngine]
  private var playAnalysisEngine:     Option[PlayAnalysisEngine]     = Option.empty[PlayAnalysisEngine]
  private var playedAnalysisEngine:   Option[PlayedAnalysisEngine]   = Option.empty[PlayedAnalysisEngine]
  private var playedWithTokenAnalysisEngine: Option[PlayedWithTokenAnalysisEngine] =
    Option.empty[PlayedWithTokenAnalysisEngine]
  private var readyAnalysisEngine:        Option[ReadyAnalysisEngine]        = Option.empty[ReadyAnalysisEngine]
  private var searchResultAnalysisEngine: Option[SearchResultAnalysisEngine] = Option.empty[SearchResultAnalysisEngine]
  private var changeLanguageAnalysisEngine: Option[ChangeLanguageAnalysisEngine] =
    Option.empty[ChangeLanguageAnalysisEngine]
  private var changeUserEmailAnalysisEngine: Option[ChangeUserEmailAnalysisEngine] =
    Option.empty[ChangeUserEmailAnalysisEngine]
  private var changeUserNameAnalysisEngine: Option[ChangeUserNameAnalysisEngine] =
    Option.empty[ChangeUserNameAnalysisEngine]
  private var changeUserPasswordAnalysisEngine: Option[ChangeUserPasswordAnalysisEngine] =
    Option.empty[ChangeUserPasswordAnalysisEngine]
  private var getSettingsAnalysisEngine: Option[GetSettingsAnalysisEngine] = Option.empty[GetSettingsAnalysisEngine]
  private var settingsAnalysisEngine:    Option[SettingsAnalysisEngine]    = Option.empty[SettingsAnalysisEngine]
  private var authorizationRequestAnalysisEngine: Option[AuthorizationRequestAnalysisEngine] =
    Option.empty[AuthorizationRequestAnalysisEngine]
  private var authorizationRequestAcceptedResponseAnalysisEngine
      : Option[AuthorizationRequestAcceptedResponseAnalysisEngine] =
    Option.empty[AuthorizationRequestAcceptedResponseAnalysisEngine]
  private var authorizationRequestAcceptedAnalysisEngine: Option[AuthorizationRequestAcceptedAnalysisEngine] =
    Option.empty[AuthorizationRequestAcceptedAnalysisEngine]
  private var renewAvatarTokenAnalysisEngine: Option[RenewAvatarTokenAnalysisEngine] =
    Option.empty[RenewAvatarTokenAnalysisEngine]
  private var createHumanPlayerEngine: Option[CreateHumanPlayerAnalysisEngine] =
    Option.empty[CreateHumanPlayerAnalysisEngine]
  private var createOnymousEngine: Option[CreateOnymousAudienceAnalysisEngine] =
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

  override def create: LobbyProcessingEngine = {
    new LobbyProcessingEngine(
      pongAnalysisEngine,
      pingAnalysisEngine,
      waitingPageAnalysisEngine,
      lobbyAnalysisEngine,
      enterLobbyAnalysisEngine,
      getAvatarInfoAnalysisEngine,
      avatarInfoAnalysisEngine,
      selectVillageAnalysisEngine,
      leaveWaitingPageAnalysisEngine,
      kickOutPlayerAnalysisEngine,
      buildVillageAnalysisEngine,
      advancedSearchAnalysisEngine,
      idSearchAnalysisEngine,
      playAnalysisEngine,
      playedAnalysisEngine,
      playedWithTokenAnalysisEngine,
      readyAnalysisEngine,
      searchResultAnalysisEngine,
      changeLanguageAnalysisEngine,
      changeUserEmailAnalysisEngine,
      changeUserNameAnalysisEngine,
      changeUserPasswordAnalysisEngine,
      getSettingsAnalysisEngine,
      settingsAnalysisEngine,
      authorizationRequestAnalysisEngine,
      authorizationRequestAcceptedResponseAnalysisEngine,
      authorizationRequestAcceptedAnalysisEngine,
      renewAvatarTokenAnalysisEngine,
      createHumanPlayerEngine,
      createOnymousEngine,
      createRobotPlayerEngine,
      deleteAvatarEngine,
      runRobotPlayerInTheBackgroundEngine,
      stopRobotPlayerEngine,
      humanPlayerSelectionPageEngine,
      onymousAudienceSelectionPageEngine,
      robotPlayerSelectionPageEngine,
      changeAvatarEngine,
      enterAvatarSelectionPageEngine
    )
  }

  def set(pongAnalysisEngine: PongAnalysisEngine): LobbyProcessingEngineFactory = {
    this.pongAnalysisEngine = Option(pongAnalysisEngine)
    this
  }

  def set(pingAnalysisEngine: PingAnalysisEngine): LobbyProcessingEngineFactory = {
    this.pingAnalysisEngine = Option(pingAnalysisEngine)
    this
  }

  def set(waitingPageAnalysisEngine: WaitingPageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.waitingPageAnalysisEngine = Option(waitingPageAnalysisEngine)
    this
  }

  def set(lobbyAnalysisEngine: LobbyAnalysisEngine): LobbyProcessingEngineFactory = {
    this.lobbyAnalysisEngine = Option(lobbyAnalysisEngine)
    this
  }

  def set(enterLobbyAnalysisEngine: EnterLobbyAnalysisEngine): LobbyProcessingEngineFactory = {
    this.enterLobbyAnalysisEngine = Option(enterLobbyAnalysisEngine)
    this
  }

  def set(getAvatarInfoAnalysisEngine: GetAvatarInfoAnalysisEngine): LobbyProcessingEngineFactory = {
    this.getAvatarInfoAnalysisEngine = Option(getAvatarInfoAnalysisEngine)
    this
  }

  def set(avatarInfoAnalysisEngine: AvatarInfoAnalysisEngine): LobbyProcessingEngineFactory = {
    this.avatarInfoAnalysisEngine = Option(avatarInfoAnalysisEngine)
    this
  }

  def set(selectVillageAnalysisEngine: SelectVillageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.selectVillageAnalysisEngine = Option(selectVillageAnalysisEngine)
    this
  }

  def set(leaveWaitingPageAnalysisEngine: LeaveWaitingPageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.leaveWaitingPageAnalysisEngine = Option(leaveWaitingPageAnalysisEngine)
    this
  }

  def set(kickOutPlayerAnalysisEngine: KickOutPlayerAnalysisEngine): LobbyProcessingEngineFactory = {
    this.kickOutPlayerAnalysisEngine = Option(kickOutPlayerAnalysisEngine)
    this
  }

  def set(buildVillageAnalysisEngine: BuildVillageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.buildVillageAnalysisEngine = Option(buildVillageAnalysisEngine)
    this
  }

  def set(advancedSearchAnalysisEngine: AdvancedSearchAnalysisEngine): LobbyProcessingEngineFactory = {
    this.advancedSearchAnalysisEngine = Option(advancedSearchAnalysisEngine)
    this
  }

  def set(idSearchAnalysisEngine: IdSearchAnalysisEngine): LobbyProcessingEngineFactory = {
    this.idSearchAnalysisEngine = Option(idSearchAnalysisEngine)
    this
  }

  def set(playAnalysisEngine: PlayAnalysisEngine): LobbyProcessingEngineFactory = {
    this.playAnalysisEngine = Option(playAnalysisEngine)
    this
  }

  def set(playedAnalysisEngine: PlayedAnalysisEngine): LobbyProcessingEngineFactory = {
    this.playedAnalysisEngine = Option(playedAnalysisEngine)
    this
  }

  def set(playedWithTokenAnalysisEngine: PlayedWithTokenAnalysisEngine): LobbyProcessingEngineFactory = {
    this.playedWithTokenAnalysisEngine = Option(playedWithTokenAnalysisEngine)
    this
  }

  def set(readyAnalysisEngine: ReadyAnalysisEngine): LobbyProcessingEngineFactory = {
    this.readyAnalysisEngine = Option(readyAnalysisEngine)
    this
  }

  def set(searchResultAnalysisEngine: SearchResultAnalysisEngine): LobbyProcessingEngineFactory = {
    this.searchResultAnalysisEngine = Option(searchResultAnalysisEngine)
    this
  }

  def set(changeLanguageAnalysisEngine: ChangeLanguageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeLanguageAnalysisEngine = Option(changeLanguageAnalysisEngine)
    this
  }

  def set(changeUserEmailAnalysisEngine: ChangeUserEmailAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeUserEmailAnalysisEngine = Option(changeUserEmailAnalysisEngine)
    this
  }

  def set(changeUserNameAnalysisEngine: ChangeUserNameAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeUserNameAnalysisEngine = Option(changeUserNameAnalysisEngine)
    this
  }

  def set(changeUserPasswordAnalysisEngine: ChangeUserPasswordAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeUserPasswordAnalysisEngine = Option(changeUserPasswordAnalysisEngine)
    this
  }

  def set(getSettingsAnalysisEngine: GetSettingsAnalysisEngine): LobbyProcessingEngineFactory = {
    this.getSettingsAnalysisEngine = Option(getSettingsAnalysisEngine)
    this
  }

  def set(settingsAnalysisEngine: SettingsAnalysisEngine): LobbyProcessingEngineFactory = {
    this.settingsAnalysisEngine = Option(settingsAnalysisEngine)
    this
  }

  def set(authorizationRequestAnalysisEngine: AuthorizationRequestAnalysisEngine): LobbyProcessingEngineFactory = {
    this.authorizationRequestAnalysisEngine = Option(authorizationRequestAnalysisEngine)
    this
  }

  def set(
      authorizationRequestAcceptedResponseAnalysisEngine: AuthorizationRequestAcceptedResponseAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.authorizationRequestAcceptedResponseAnalysisEngine = Option(authorizationRequestAcceptedResponseAnalysisEngine)
    this
  }

  def set(
      authorizationRequestAcceptedAnalysisEngine: AuthorizationRequestAcceptedAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.authorizationRequestAcceptedAnalysisEngine = Option(authorizationRequestAcceptedAnalysisEngine)
    this
  }

  def set(renewAvatarTokenAnalysisEngine: RenewAvatarTokenAnalysisEngine): LobbyProcessingEngineFactory = {
    this.renewAvatarTokenAnalysisEngine = Option(renewAvatarTokenAnalysisEngine)
    this
  }

  def set(createHumanPlayerAnalysisEngine: CreateHumanPlayerAnalysisEngine): LobbyProcessingEngineFactory = {
    this.createHumanPlayerEngine = Option(createHumanPlayerAnalysisEngine)
    this
  }

  def set(createOnymousAudienceAnalysisEngine: CreateOnymousAudienceAnalysisEngine): LobbyProcessingEngineFactory = {
    this.createOnymousEngine = Option(createOnymousAudienceAnalysisEngine)
    this
  }

  def set(createRobotPlayerAnalysisEngine: CreateRobotPlayerAnalysisEngine): LobbyProcessingEngineFactory = {
    this.createRobotPlayerEngine = Option(createRobotPlayerAnalysisEngine)
    this
  }

  def set(deleteAvatarAnalysisEngine: DeleteAvatarAnalysisEngine): LobbyProcessingEngineFactory = {
    this.deleteAvatarEngine = Option(deleteAvatarAnalysisEngine)
    this
  }

  def set(
      runRobotPlayerInTheBackgroundAnalysisEngine: RunRobotPlayerInTheBackgroundAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.runRobotPlayerInTheBackgroundEngine = Option(runRobotPlayerInTheBackgroundAnalysisEngine)
    this
  }

  def set(stopRobotPlayerAnalysisEngine: StopRobotPlayerAnalysisEngine): LobbyProcessingEngineFactory = {
    this.stopRobotPlayerEngine = Option(stopRobotPlayerAnalysisEngine)
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
