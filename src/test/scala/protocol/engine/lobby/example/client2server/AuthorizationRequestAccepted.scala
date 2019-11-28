package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class AuthorizationRequestAccepted(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = AuthorizationRequestAccepted.`type`
}

object AuthorizationRequestAccepted {
  val `type`: String = "lobby.client2server.AuthorizationRequestAccepted"
}
