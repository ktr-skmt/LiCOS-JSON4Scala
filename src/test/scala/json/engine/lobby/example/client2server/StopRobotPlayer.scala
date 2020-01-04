package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class StopRobotPlayer(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = StopRobotPlayer.`type`
}

object StopRobotPlayer {
  val `type`: String = "StopRobotPlayer"
}
