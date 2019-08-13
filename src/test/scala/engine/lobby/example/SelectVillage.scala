package engine.lobby.example

import engine.ClientToServerLobbyExample

case class SelectVillage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = SelectVillage.`type`
}

object SelectVillage {
  val `type`: String = "SelectVillage"
}
