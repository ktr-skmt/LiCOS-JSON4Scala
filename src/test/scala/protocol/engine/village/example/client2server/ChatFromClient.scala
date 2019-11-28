package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class ChatFromClient(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ChatFromClient.`type`
}

object ChatFromClient {
  val `type`: String = "village.client2server.ChatFromClient"
}
