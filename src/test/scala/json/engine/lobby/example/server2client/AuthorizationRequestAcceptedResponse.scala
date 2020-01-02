package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class AuthorizationRequestAcceptedResponse(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = AuthorizationRequestAcceptedResponse.`type`
}

object AuthorizationRequestAcceptedResponse {
  val `type`: String = "AuthorizationRequestAcceptedResponse"
}
