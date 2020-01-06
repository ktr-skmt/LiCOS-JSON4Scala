package licos.json2protocol.lobby

import licos.json.element.lobby.client2server.{
  JsonAdvancedSearch,
  JsonAuthorizationRequestAccepted,
  JsonBuildVillage,
  JsonChangeLang,
  JsonChangeUserEmail,
  JsonChangeUserName,
  JsonChangeUserPassword,
  JsonCreateHumanPlayer,
  JsonCreateOnymousAudience,
  JsonCreateRobotPlayer,
  JsonDeleteAvatar,
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
  JsonRunRobotPlayerInTheForeground,
  JsonSelectHumanPlayer,
  JsonSelectOnymousAudience,
  JsonSelectVillage,
  JsonStopRobotPlayer
}
import licos.json.element.lobby.server2client.{
  JsonAuthorizationRequest,
  JsonAuthorizationRequestAcceptedResponse,
  JsonAvatarInfo,
  JsonGetAvatarInfo,
  JsonHumanPlayerSelectionPage,
  JsonLobby,
  JsonNewAvatarToken,
  JsonOnymousAudienceSelectionPage,
  JsonPing,
  JsonPlayed,
  JsonRobotPlayerSelectionPage,
  JsonSearchResult,
  JsonSettings,
  JsonWaitingPage
}
import licos.json.element.lobby.server2server.JsonPlayedWithToken
import licos.json.flow.{FlowController, LobbyFlowController}
import licos.json2protocol.Json2Protocol
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.{
  AdvancedSearchProtocol,
  AuthorizationRequestAcceptedProtocol,
  BuildVillageProtocol,
  ChangeLangProtocol,
  ChangeUserEmailProtocol,
  ChangeUserNameProtocol,
  ChangeUserPasswordProtocol,
  CreateHumanPlayerProtocol,
  CreateOnymousAudienceProtocol,
  CreateRobotPlayerProtocol,
  DeleteAvatarProtocol,
  EnterLobbyProtocol,
  GetAvatarInfoProtocol,
  GetSettingsProtocol,
  IdSearchProtocol,
  KickOutPlayerProtocol,
  LeaveWaitingPageProtocol,
  PlayProtocol,
  PongProtocol,
  ReadyProtocol,
  RenewAvatarTokenProtocol,
  RunRobotPlayerInTheBackgroundProtocol,
  RunRobotPlayerInTheForegroundProtocol,
  SelectHumanPlayerProtocol,
  SelectOnymousAudienceProtocol,
  SelectVillageProtocol,
  StopRobotPlayerProtocol
}
import licos.protocol.element.lobby.server2client.{
  AuthorizationRequestAcceptedResponseProtocol,
  AuthorizationRequestProtocol,
  AvatarInfoProtocol,
  HumanPlayerSelectionPageProtocol,
  LobbyProtocol,
  NewAvatarTokenProtocol,
  OnymousAudienceSelectionPageProtocol,
  PingProtocol,
  PlayedProtocol,
  RobotPlayerSelectionPageProtocol,
  SearchResultProtocol,
  SettingsProtocol,
  WaitingPageProtocol
}
import licos.protocol.element.lobby.server2server.PlayedWithTokenProtocol
import play.api.libs.json.JsValue

object Json2LobbyMessageProtocol extends Json2Protocol {
  override protected val flowController: FlowController = new LobbyFlowController()

  def toProtocolOpt(json: JsValue): Option[LobbyMessageProtocol] = {
    flowController.flow(json) match {
      case Right(json: JsonPong) =>
        PongProtocol.read(json)
      case Right(json: JsonPing) =>
        PingProtocol.read(json)
      case Right(json: JsonWaitingPage) =>
        WaitingPageProtocol.read(json)
      case Right(json: JsonLobby) =>
        LobbyProtocol.read(json)
      case Right(json: JsonEnterLobby) =>
        EnterLobbyProtocol.read(json)
      case Right(json: JsonGetAvatarInfo) =>
        GetAvatarInfoProtocol.read(json)
      case Right(json: JsonAvatarInfo) =>
        AvatarInfoProtocol.read(json)
      case Right(json: JsonSelectVillage) =>
        SelectVillageProtocol.read(json)
      case Right(json: JsonLeaveWaitingPage) =>
        LeaveWaitingPageProtocol.read(json)
      case Right(json: JsonKickOutPlayer) =>
        KickOutPlayerProtocol.read(json)
      case Right(json: JsonBuildVillage) =>
        BuildVillageProtocol.read(json)
      case Right(json: JsonAdvancedSearch) =>
        AdvancedSearchProtocol.read(json)
      case Right(json: JsonIdSearch) =>
        IdSearchProtocol.read(json)
      case Right(json: JsonPlay) =>
        PlayProtocol.read(json)
      case Right(json: JsonPlayed) =>
        PlayedProtocol.read(json)
      case Right(json: JsonPlayedWithToken) =>
        PlayedWithTokenProtocol.read(json)
      case Right(json: JsonReady) =>
        ReadyProtocol.read(json)
      case Right(json: JsonSearchResult) =>
        SearchResultProtocol.read(json)
      case Right(json: JsonChangeLang) =>
        ChangeLangProtocol.read(json)
      case Right(json: JsonChangeUserEmail) =>
        ChangeUserEmailProtocol.read(json)
      case Right(json: JsonChangeUserName) =>
        ChangeUserNameProtocol.read(json)
      case Right(json: JsonChangeUserPassword) =>
        ChangeUserPasswordProtocol.read(json)
      case Right(json: JsonGetSettings) =>
        GetSettingsProtocol.read(json)
      case Right(json: JsonSettings) =>
        SettingsProtocol.read(json)
      case Right(json: JsonAuthorizationRequest) =>
        AuthorizationRequestProtocol.read(json)
      case Right(json: JsonAuthorizationRequestAcceptedResponse) =>
        AuthorizationRequestAcceptedResponseProtocol.read(json)
      case Right(json: JsonAuthorizationRequestAccepted) =>
        AuthorizationRequestAcceptedProtocol.read(json)
      case Right(json: JsonRenewAvatarToken) =>
        RenewAvatarTokenProtocol.read(json)
      case Right(json: JsonNewAvatarToken) =>
        NewAvatarTokenProtocol.read(json)
      case Right(json: JsonCreateHumanPlayer) =>
        CreateHumanPlayerProtocol.read(json)
      case Right(json: JsonCreateOnymousAudience) =>
        CreateOnymousAudienceProtocol.read(json)
      case Right(json: JsonCreateRobotPlayer) =>
        CreateRobotPlayerProtocol.read(json)
      case Right(json: JsonDeleteAvatar) =>
        DeleteAvatarProtocol.read(json)
      case Right(json: JsonRunRobotPlayerInTheBackground) =>
        RunRobotPlayerInTheBackgroundProtocol.read(json)
      case Right(json: JsonRunRobotPlayerInTheForeground) =>
        RunRobotPlayerInTheForegroundProtocol.read(json)
      case Right(json: JsonSelectHumanPlayer) =>
        SelectHumanPlayerProtocol.read(json)
      case Right(json: JsonSelectOnymousAudience) =>
        SelectOnymousAudienceProtocol.read(json)
      case Right(json: JsonStopRobotPlayer) =>
        StopRobotPlayerProtocol.read(json)
      case Right(json: JsonHumanPlayerSelectionPage) =>
        HumanPlayerSelectionPageProtocol.read(json)
      case Right(json: JsonOnymousAudienceSelectionPage) =>
        OnymousAudienceSelectionPageProtocol.read(json)
      case Right(json: JsonRobotPlayerSelectionPage) =>
        RobotPlayerSelectionPageProtocol.read(json)
      case _ =>
        Option.empty[LobbyMessageProtocol]
    }
  }
}
