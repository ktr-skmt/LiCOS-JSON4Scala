package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class OnymousAudienceChatFromClient(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = OnymousAudienceChatFromClient.`type`
}

object OnymousAudienceChatFromClient {
  val `type`: String = "village.client2server.OnymousAudienceChatFromClient"
}
