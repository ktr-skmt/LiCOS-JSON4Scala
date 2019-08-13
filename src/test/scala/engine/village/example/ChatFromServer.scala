package engine.village.example

import engine.ServerToClientVillageExample

case class ChatFromServer(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = ChatFromServer.`type`
}

object ChatFromServer {
  val `type`: String = "ChatFromServer"
}