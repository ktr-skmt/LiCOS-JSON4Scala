package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class Played(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Played.`type`
}

object Played {
  val `type`: String = "Played"
}
