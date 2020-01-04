package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class RunRobotPlayerInTheBackground(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = RunRobotPlayerInTheBackground.`type`
}

object RunRobotPlayerInTheBackground {
  val `type`: String = "RunRobotPlayerInTheBackground"
}
