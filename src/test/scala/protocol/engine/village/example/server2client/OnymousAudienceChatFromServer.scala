package protocol.engine.village.example.server2client

import protocol.engine.ServerToClientVillageExample

final case class OnymousAudienceChatFromServer(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = OnymousAudienceChatFromServer.`type`
}

object OnymousAudienceChatFromServer {
  val `type`: String = "village.server2client.OnymousAudienceChatFromServer"
}
