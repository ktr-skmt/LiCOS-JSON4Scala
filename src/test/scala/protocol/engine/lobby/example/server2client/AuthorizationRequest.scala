package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class AuthorizationRequest(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = AuthorizationRequest.`type`
}

object AuthorizationRequest {
  val `type`: String = "lobby.server2client.AuthorizationRequest"
}
