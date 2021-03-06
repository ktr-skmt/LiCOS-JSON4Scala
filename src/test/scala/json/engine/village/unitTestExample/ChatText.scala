package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

final case class ChatText(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = ChatText.`type`
}

object ChatText {
  val `type`: String = "unitTest/ChatText"
}
