package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

case class StarInfo(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = StarInfo.`type`
}

object StarInfo {
  val `type`: String = "unitTest/StarInfo"
}
