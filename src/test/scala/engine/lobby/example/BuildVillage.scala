package engine.lobby.example

import engine.ClientToServerLobbyExample

case class BuildVillage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = BuildVillage.`type`
}

object BuildVillage {
  val `type`: String = "BuildVillage"
}
