package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

final case class SubOnymousAudienceScroll(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubOnymousAudienceScroll.`type`
}

object SubOnymousAudienceScroll {
  val `type`: String = "unitTest/SubOnymousAudienceScroll"
}
