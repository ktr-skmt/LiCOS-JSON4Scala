package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class ReceivedFlavorTextMessage(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ReceivedFlavorTextMessage.`type`
}

object ReceivedFlavorTextMessage {
  val `type`: String = "village.client2server.ReceivedFlavorTextMessage"
}
