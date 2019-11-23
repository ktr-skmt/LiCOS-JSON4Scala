package json.engine.village.example.client2server

import json.engine.ClientToServerVillageExample

case class OnymousAudienceChat(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = OnymousAudienceChat.`type`
}

object OnymousAudienceChat {
  val `type`: String = "OnymousAudienceChatFromClient"
}
