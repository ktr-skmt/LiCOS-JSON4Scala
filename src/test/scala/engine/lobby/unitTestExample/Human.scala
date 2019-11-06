package engine.lobby.unitTestExample

import engine.LobbyUnitTestExample

case class Human(filePath: String) extends LobbyUnitTestExample(filePath) {
  override val `type`: String = Human.`type`
}

object Human {
  val `type`: String = "unitTest/Human"
}
