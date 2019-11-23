package json.engine.village.example.client2server

import json.engine.ClientToServerVillageExample

case class ReceivedChatMessage(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ReceivedChatMessage.`type`
}

object ReceivedChatMessage {
  val `type`: String = "ReceivedChatMessage"
}
