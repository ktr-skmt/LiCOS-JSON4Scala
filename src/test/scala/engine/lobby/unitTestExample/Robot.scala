package engine.lobby.unitTestExample

import engine.LobbyUnitTestExample

case class Robot(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = Robot.`type`
}

object Robot {
  val `type`: String = "unitTest/Robot"
}
