package json.engine.lobby.unitTestExample

import json.engine.LobbyUnitTestExample

final case class RoleSetting(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = RoleSetting.`type`
}

object RoleSetting {
  val `type`: String = "unitTest/RoleSetting"
}
