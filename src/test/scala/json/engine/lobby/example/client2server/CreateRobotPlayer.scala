package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class CreateRobotPlayer(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = CreateRobotPlayer.`type`
}

object CreateRobotPlayer {
  val `type`: String = "CreateRobotPlayer"
}
