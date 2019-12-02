package protocol.engine.village.example.server2client

import protocol.engine.ServerToClientVillageExample

final case class AnonymousAudienceChatFromServer(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = AnonymousAudienceChatFromServer.`type`
}

object AnonymousAudienceChatFromServer {
  val `type`: String = "village.server2client.AnonymousAudienceChatFromServer"
}
