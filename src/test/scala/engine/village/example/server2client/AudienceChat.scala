package engine.village.example.server2client

import engine.ServerToClientVillageExample

case class AudienceChat(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = AudienceChat.`type`
}

object AudienceChat {
  val `type`: String = "AudienceChatFromServer"
}