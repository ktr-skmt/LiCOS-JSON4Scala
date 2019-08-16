package engine.village.example.server2client

import engine.ClientToServerVillageExample

case class AudienceChat(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = AudienceChat.`type`
}

object AudienceChat {
  val `type`: String = "AudienceChatFromServer"
}