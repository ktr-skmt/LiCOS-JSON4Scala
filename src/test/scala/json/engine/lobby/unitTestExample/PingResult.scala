package json.engine.lobby.unitTestExample

import json.engine.LobbyUnitTestExample

final case class PingResult(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = PingResult.`type`
}

object PingResult {
  val `type`: String = "unitTest/PingResult"
}
