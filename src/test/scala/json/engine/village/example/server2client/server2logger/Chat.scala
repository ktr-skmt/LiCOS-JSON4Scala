package json.engine.village.example.server2client.server2logger

import json.engine.ServerToLoggerVillageExample

final case class Chat(filePath: String) extends ServerToLoggerVillageExample(filePath) {
  override val `type`: String = Chat.`type`
}

object Chat {
  val `type`: String = "ChatFromServer"
}
