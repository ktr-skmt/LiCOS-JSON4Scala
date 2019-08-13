package engine.village.example

import engine.ClientToServerVillageExample

case class ChatFromClient(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ChatFromClient.`type`
}

object ChatFromClient {
  val `type`: String = "ChatFromClient"
}