package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

final case class SubChatFromClient(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubChatFromClient.`type`
}

object SubChatFromClient {
  val `type`: String = "unitTest/SubChatFromClient"
}
