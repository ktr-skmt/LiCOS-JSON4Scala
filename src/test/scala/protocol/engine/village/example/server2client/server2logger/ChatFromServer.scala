package protocol.engine.village.example.server2client.server2logger

import protocol.engine.ServerToClientVillageExample

final case class ChatFromServer(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = ChatFromServer.`type`
}

object ChatFromServer {
  val `type`: String = "village.server2client.server2logger.ChatFromServer"
}
