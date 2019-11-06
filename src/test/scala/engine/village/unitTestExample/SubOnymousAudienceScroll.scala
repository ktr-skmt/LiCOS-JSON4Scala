package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubOnymousAudienceScroll(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubOnymousAudienceScroll.`type`
}

object SubOnymousAudienceScroll {
  val `type`: String = "unitTest/SubOnymousAudienceScroll"
}
