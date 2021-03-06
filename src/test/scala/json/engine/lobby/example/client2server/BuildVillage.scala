package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class BuildVillage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = BuildVillage.`type`
}

object BuildVillage {
  val `type`: String = "BuildVillage"
}
