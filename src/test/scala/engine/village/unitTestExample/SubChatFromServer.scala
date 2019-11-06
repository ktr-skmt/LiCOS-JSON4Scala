package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubChatFromServer(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubChatFromServer.`type`
}

object SubChatFromServer {
  val `type`: String = "unitTest/SubChatFromServer"
}
