package engine.village.example.client2server

import engine.ClientToServerVillageExample

case class AudienceChat(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = AudienceChat.`type`
}

object AudienceChat {
  val `type`: String = "AudienceChatFromClient"
}
