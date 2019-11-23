package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

case class Name(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = Name.`type`
}

object Name {
  val `type`: String = "unitTest/Name"
}
