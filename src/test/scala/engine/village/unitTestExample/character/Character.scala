package engine.village.unitTestExample.character

import engine.VillageUnitTestExample

case class Character(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = Character.`type`
}

object Character {
  val `type`: String = "unitTest/Character"
}
