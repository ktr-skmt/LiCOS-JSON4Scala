package json.engine.village.unitTestExample.role

import json.engine.VillageUnitTestExample

case class ResultRole(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = ResultRole.`type`
}

object ResultRole {
  val `type`: String = "unitTest/ResultRole"
}
