package json.engine.lobby.example

import json.engine.ClientToServerLobbyExample

case class Ready(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = Ready.`type`
}

object Ready {
  val `type`: String = "Ready"
}