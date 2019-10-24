package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubOnymousAudienceChat(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubOnymousAudienceChat.`type`
}

object SubOnymousAudienceChat {
  val `type`: String = "unitTest/SubOnymousAudienceChat"
}