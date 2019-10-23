package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubChatFromClient(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubChatFromClient.`type`
}

object SubChatFromClient {
  val `type`: String = "unitTest/SubChatFromClient"
}