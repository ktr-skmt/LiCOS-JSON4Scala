package json.engine.lobby.unitTestExample

import json.engine.LobbyUnitTestExample

final case class PlayerInWaitingPage(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = PlayerInWaitingPage.`type`
}

object PlayerInWaitingPage {
  val `type`: String = "unitTest/PlayerInWaitingPage"
}
