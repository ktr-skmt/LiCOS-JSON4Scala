package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

case class BoardResult(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = BoardResult.`type`
}

object BoardResult {
  val `type`: String = "unitTest/BoardResult"
}
