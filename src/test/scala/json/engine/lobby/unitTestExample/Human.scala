package json.engine.lobby.unitTestExample

import json.engine.LobbyUnitTestExample

final case class Human(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = Human.`type`
}

object Human {
  val `type`: String = "unitTest/Human"
}
