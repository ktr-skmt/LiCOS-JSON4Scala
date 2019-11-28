package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class ReceivedSystemMessage(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ReceivedSystemMessage.`type`
}

object ReceivedSystemMessage {
  val `type`: String = "village.client2server.ReceivedSystemMessage"
}
