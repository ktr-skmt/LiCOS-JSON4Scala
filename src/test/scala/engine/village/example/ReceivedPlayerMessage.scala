package engine.village.example

import engine.ClientToServerVillageExample

case class ReceivedPlayerMessage(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ReceivedPlayerMessage.`type`
}

object ReceivedPlayerMessage {
  val `type`: String = "ReceivedPlayerMessage"
}