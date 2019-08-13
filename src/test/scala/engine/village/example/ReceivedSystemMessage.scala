package engine.village.example

import engine.ClientToServerVillageExample

case class ReceivedSystemMessage(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ReceivedSystemMessage.`type`
}

object ReceivedSystemMessage {
  val `type`: String = "ReceivedSystemMessage"
}