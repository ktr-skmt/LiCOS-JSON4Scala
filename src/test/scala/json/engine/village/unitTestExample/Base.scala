package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

case class Base(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = Base.`type`
}

object Base {
  val `type`: String = "unitTest/Base"
}