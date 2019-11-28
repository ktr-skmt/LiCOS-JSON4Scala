package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class AnonymousAudienceChatFromClient(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = AnonymousAudienceChatFromClient.`type`
}

object AnonymousAudienceChatFromClient {
  val `type`: String = "village.client2server.AnonymousAudienceChatFromClient"
}
