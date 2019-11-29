package protocol.engine.village.example.server2client.server2logger

import protocol.engine.ServerToLoggerVillageExample

final case class ChatFromServer(filePath: String) extends ServerToLoggerVillageExample(filePath) {
  override val `type`: String = ChatFromServer.`type`
}

object ChatFromServer {
  val `type`: String = "village.server2client.server2logger.ChatFromServer"
}
