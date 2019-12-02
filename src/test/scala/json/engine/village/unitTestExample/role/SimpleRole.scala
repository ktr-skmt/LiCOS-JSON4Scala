package json.engine.village.unitTestExample.role

import json.engine.VillageUnitTestExample

final case class SimpleRole(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SimpleRole.`type`
}

object SimpleRole {
  val `type`: String = "unitTest/SimpleRole"
}
