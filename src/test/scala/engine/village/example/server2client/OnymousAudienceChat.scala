package engine.village.example.server2client

import engine.ServerToClientVillageExample

case class OnymousAudienceChat(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = OnymousAudienceChat.`type`
}

object OnymousAudienceChat {
  val `type`: String = "OnymousAudienceChatFromServer"
}
