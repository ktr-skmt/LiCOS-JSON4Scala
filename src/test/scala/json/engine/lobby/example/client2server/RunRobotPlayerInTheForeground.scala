package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class RunRobotPlayerInTheForeground(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = RunRobotPlayerInTheForeground.`type`
}

object RunRobotPlayerInTheForeground {
  val `type`: String = "RunRobotPlayerInTheForeground"
}
