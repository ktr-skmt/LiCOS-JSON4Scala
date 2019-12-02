package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class BuildVillage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = BuildVillage.`type`
}

object BuildVillage {
  val `type`: String = "lobby.client2server.BuildVillage"
}
