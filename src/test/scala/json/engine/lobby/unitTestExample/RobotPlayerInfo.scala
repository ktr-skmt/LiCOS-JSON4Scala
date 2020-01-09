package json.engine.lobby.unitTestExample

import json.engine.LobbyUnitTestExample

final case class RobotPlayerInfo(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = RobotPlayerInfo.`type`
}

object RobotPlayerInfo {
  val `type`: String = "unitTest/RobotPlayerInfo"
}
