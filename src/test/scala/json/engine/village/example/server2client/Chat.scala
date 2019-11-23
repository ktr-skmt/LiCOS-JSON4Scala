package json.engine.village.example.server2client

import json.engine.ServerToClientVillageExample

case class Chat(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = Chat.`type`
}

object Chat {
  val `type`: String = "ChatFromServer"
}
