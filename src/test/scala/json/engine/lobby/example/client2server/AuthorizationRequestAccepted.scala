package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class AuthorizationRequestAccepted(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = AuthorizationRequestAccepted.`type`
}

object AuthorizationRequestAccepted {
  val `type`: String = "AuthorizationRequestAccepted"
}
