package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

final case class SubOnymousAudienceBoard(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubOnymousAudienceBoard.`type`
}

object SubOnymousAudienceBoard {
  val `type`: String = "unitTest/SubOnymousAudienceBoard"
}
