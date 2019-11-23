package json.engine.lobby.example

import json.engine.ServerToClientLobbyExample

case class Played(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Played.`type`
}

object Played {
  val `type`: String = "Played"
}