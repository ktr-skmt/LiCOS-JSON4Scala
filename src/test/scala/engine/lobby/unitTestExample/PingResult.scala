package engine.lobby.unitTestExample

import engine.LobbyUnitTestExample

case class PingResult(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = PingResult.`type`
}

object PingResult {
  val `type`: String = "unitTest/PingResult"
}