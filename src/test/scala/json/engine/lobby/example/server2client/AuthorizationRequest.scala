package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class AuthorizationRequest(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = AuthorizationRequest.`type`
}

object AuthorizationRequest {
  val `type`: String = "AuthorizationRequest"
}
