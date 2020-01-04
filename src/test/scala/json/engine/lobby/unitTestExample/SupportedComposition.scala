package json.engine.lobby.unitTestExample

import json.engine.LobbyUnitTestExample

final case class SupportedComposition(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = SupportedComposition.`type`
}

object SupportedComposition {
  val `type`: String = "unitTest/SupportedComposition"
}
