package engine.village.example.client2server

import engine.ClientToServerVillageExample

case class OnymousAudienceChat(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = OnymousAudienceChat.`type`
}

object OnymousAudienceChat {
  val `type`: String = "OnymousAudienceChatFromClient"
}
