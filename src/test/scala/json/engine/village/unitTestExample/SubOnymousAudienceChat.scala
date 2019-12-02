package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

final case class SubOnymousAudienceChat(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubOnymousAudienceChat.`type`
}

object SubOnymousAudienceChat {
  val `type`: String = "unitTest/SubOnymousAudienceChat"
}
