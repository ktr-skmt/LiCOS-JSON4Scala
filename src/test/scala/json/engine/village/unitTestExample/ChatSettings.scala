package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

case class ChatSettings(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = ChatSettings.`type`
}

object ChatSettings {
  val `type`: String = "unitTest/ChatSettings"
}
