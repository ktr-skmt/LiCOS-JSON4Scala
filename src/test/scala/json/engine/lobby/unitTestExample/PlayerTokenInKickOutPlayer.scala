package json.engine.lobby.unitTestExample

import json.engine.LobbyUnitTestExample

final case class PlayerTokenInKickOutPlayer(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = PlayerTokenInKickOutPlayer.`type`
}

object PlayerTokenInKickOutPlayer {
  val `type`: String = "unitTest/PlayerInKickOutPlayer"
}
