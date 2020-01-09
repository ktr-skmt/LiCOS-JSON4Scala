package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class CreateOnymousAudience(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = CreateOnymousAudience.`type`
}

object CreateOnymousAudience {
  val `type`: String = "CreateOnymousAudience"
}
