package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class SelectVillage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = SelectVillage.`type`
}

object SelectVillage {
  val `type`: String = "lobby.client2server.SelectVillage"
}
