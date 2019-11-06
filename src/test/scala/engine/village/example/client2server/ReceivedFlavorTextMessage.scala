package engine.village.example.client2server

import engine.ClientToServerVillageExample

case class ReceivedFlavorTextMessage(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ReceivedFlavorTextMessage.`type`
}

object ReceivedFlavorTextMessage {
  val `type`: String = "ReceivedFlavorTextMessage"
}
