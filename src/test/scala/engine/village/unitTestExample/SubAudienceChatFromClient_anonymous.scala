package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubAudienceChatFromClient_anonymous(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubAudienceChatFromClient_anonymous.`type`
}

object SubAudienceChatFromClient_anonymous {
  val `type`: String = "unitTest/SubAudienceChatFromClient_anonymous"
}