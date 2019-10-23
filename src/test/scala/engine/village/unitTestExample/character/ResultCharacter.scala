package engine.village.unitTestExample.character

import engine.VillageUnitTestExample

case class ResultCharacter(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = ResultCharacter.`type`
}

object ResultCharacter {
  val `type`: String = "unitTest/ResultCharacter"
}