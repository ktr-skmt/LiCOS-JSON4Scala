package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class ReceivedChatMessage(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ReceivedChatMessage.`type`
}

object ReceivedChatMessage {
  val `type`: String = "village.client2server.ReceivedChatMessage"
}
