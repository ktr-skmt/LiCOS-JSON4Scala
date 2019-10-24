package engine.lobby.unitTestExample

import engine.LobbyUnitTestExample

case class HostPlayer(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = HostPlayer.`type`
}

object HostPlayer {
  val `type`: String = "unitTest/HostPlayer"
}