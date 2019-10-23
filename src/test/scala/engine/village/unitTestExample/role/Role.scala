package engine.village.unitTestExample.role

import engine.VillageUnitTestExample

case class Role(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = Role.`type`
}

object Role {
  val `type`: String = "unitTest/Role"
}