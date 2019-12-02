package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class Ready(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = Ready.`type`
}

object Ready {
  val `type`: String = "lobby.client2server.Ready"
}
