package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

final case class SubGameResult(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubGameResult.`type`
}

object SubGameResult {
  val `type`: String = "unitTest/SubGameResult"
}
