package engine.lobby.unitTestExample

import engine.LobbyUnitTestExample

case class PlayerSetting(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = PlayerSetting.`type`
}

object PlayerSetting {
  val `type`: String = "unitTest/PlayerSetting"
}