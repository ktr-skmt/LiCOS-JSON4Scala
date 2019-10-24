package engine.lobby.unitTestExample

import engine.LobbyUnitTestExample

case class RoleSetting(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = RoleSetting.`type`
}

object RoleSetting {
  val `type`: String = "unitTest/RoleSetting"
}