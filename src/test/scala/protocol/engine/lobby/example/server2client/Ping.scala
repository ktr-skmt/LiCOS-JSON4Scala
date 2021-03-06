package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class Ping(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Ping.`type`
}

object Ping {
  val `type`: String = "lobby.server2client.Ping"
}
