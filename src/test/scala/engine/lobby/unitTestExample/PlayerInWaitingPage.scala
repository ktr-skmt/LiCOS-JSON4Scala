package engine.lobby.unitTestExample

import engine.LobbyUnitTestExample

case class PlayerInWaitingPage(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = PlayerInWaitingPage.`type`
}

object PlayerInWaitingPage {
  val `type`: String = "unitTest/PlayerInWaitingPage"
}