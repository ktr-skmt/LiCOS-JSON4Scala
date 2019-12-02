package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class AuthorizationRequestAcceptedResponse(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = AuthorizationRequestAcceptedResponse.`type`
}

object AuthorizationRequestAcceptedResponse {
  val `type`: String = "lobby.server2client.AuthorizationRequestAcceptedResponse"
}
