package engine.village.unitTestExample.role

import engine.VillageUnitTestExample

case class SimpleRole(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SimpleRole.`type`
}

object SimpleRole {
  val `type`: String = "unitTest/SimpleRole"
}