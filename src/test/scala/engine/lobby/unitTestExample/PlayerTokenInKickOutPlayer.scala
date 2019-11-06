package engine.lobby.unitTestExample

import engine.LobbyUnitTestExample

case class PlayerTokenInKickOutPlayer(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = PlayerTokenInKickOutPlayer.`type`
}

object PlayerTokenInKickOutPlayer {
  val `type`: String = "unitTest/PlayerInKickOutPlayer"
}
