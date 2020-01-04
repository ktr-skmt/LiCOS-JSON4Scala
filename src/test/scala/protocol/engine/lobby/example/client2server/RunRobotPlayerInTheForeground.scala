package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class RunRobotPlayerInTheForeground(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = RunRobotPlayerInTheForeground.`type`
}

object RunRobotPlayerInTheForeground {
  val `type`: String = "lobby.client2server.RunRobotPlayerInTheForeground"
}
