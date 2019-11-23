package json.engine.village.unitTestExample.character

import json.engine.VillageUnitTestExample

case class RoleCharacter(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = RoleCharacter.`type`
}

object RoleCharacter {
  val `type`: String = "unitTest/RoleCharacter"
}
