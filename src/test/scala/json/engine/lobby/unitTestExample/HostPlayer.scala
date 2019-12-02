package json.engine.lobby.unitTestExample

import json.engine.LobbyUnitTestExample

final case class HostPlayer(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = HostPlayer.`type`
}

object HostPlayer {
  val `type`: String = "unitTest/HostPlayer"
}
