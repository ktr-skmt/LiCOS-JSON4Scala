package json.engine.lobby.example

import json.engine.ClientToServerLobbyExample

case class SelectVillage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = SelectVillage.`type`
}

object SelectVillage {
  val `type`: String = "SelectVillage"
}