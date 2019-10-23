package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class ChatText(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = ChatText.`type`
}

object ChatText {
  val `type`: String = "unitTest/ChatText"
}