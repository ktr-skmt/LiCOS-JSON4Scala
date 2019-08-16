package engine.village.example.client2server

import engine.ServerToClientVillageExample

case class AudienceChat(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = AudienceChat.`type`
}

object AudienceChat {
  val `type`: String = "AudienceChatFromClient"
}
