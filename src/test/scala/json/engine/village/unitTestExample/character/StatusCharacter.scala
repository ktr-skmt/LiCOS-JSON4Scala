package json.engine.village.unitTestExample.character

import json.engine.VillageUnitTestExample

case class StatusCharacter(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = StatusCharacter.`type`
}

object StatusCharacter {
  val `type`: String = "unitTest/StatusCharacter"
}
