package engine.village.unitTestExample.character

import engine.VillageUnitTestExample

case class SimpleCharacter(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SimpleCharacter.`type`
}

object SimpleCharacter {
  val `type`: String = "unitTest/SimpleCharacter"
}
