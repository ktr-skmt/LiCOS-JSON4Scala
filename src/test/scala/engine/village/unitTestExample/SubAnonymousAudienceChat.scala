package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubAnonymousAudienceChat(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubAnonymousAudienceChat.`type`
}

object SubAnonymousAudienceChat {
  val `type`: String = "unitTest/SubAnonymousAudienceChat"
}
