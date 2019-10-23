package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubGameResult(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubGameResult.`type`
}

object SubGameResult {
  val `type`: String = "unitTest/SubGameResult"
}