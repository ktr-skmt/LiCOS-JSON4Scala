package json.engine.village.unitTestExample.character

import json.engine.VillageUnitTestExample

final case class ResultCharacter(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = ResultCharacter.`type`
}

object ResultCharacter {
  val `type`: String = "unitTest/ResultCharacter"
}
