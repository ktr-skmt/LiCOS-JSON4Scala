package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class Ping(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Ping.`type`
}

object Ping {
  val `type`: String = "Ping"
}
