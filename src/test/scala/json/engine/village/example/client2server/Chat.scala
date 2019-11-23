package json.engine.village.example.client2server

import json.engine.ClientToServerVillageExample

case class Chat(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Chat.`type`
}

object Chat {
  val `type`: String = "ChatFromClient"
}
