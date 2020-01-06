package json.engine.lobby.unitTestExample

import json.engine.LobbyUnitTestExample

final case class SubAvatarInfo(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = SubAvatarInfo.`type`
}

object SubAvatarInfo {
  val `type`: String = "unitTest/SubAvatarInfo"
}
