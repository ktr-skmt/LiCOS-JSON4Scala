package json.engine.village.example.client2server

import json.engine.ClientToServerVillageExample

final case class ReceivedFlavorTextMessage(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ReceivedFlavorTextMessage.`type`
}

object ReceivedFlavorTextMessage {
  val `type`: String = "ReceivedFlavorTextMessage"
}
