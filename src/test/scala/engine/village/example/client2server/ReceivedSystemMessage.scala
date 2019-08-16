package engine.village.example.client2server

import engine.ClientToServerVillageExample

case class ReceivedSystemMessage(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ReceivedSystemMessage.`type`
}

object ReceivedSystemMessage {
  val `type`: String = "ReceivedSystemMessage"
}