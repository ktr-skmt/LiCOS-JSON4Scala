package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

case class Village(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = Village.`type`
}

object Village {
  val `type`: String = "unitTest/Village"
}
