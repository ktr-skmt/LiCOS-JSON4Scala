package json.engine.lobby.unitTestExample

import json.engine.LobbyUnitTestExample

final case class Support(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = Support.`type`
}

object Support {
  val `type`: String = "unitTest/Support"
}
