package json.engine.village.example.client2server

import json.engine.ClientToServerVillageExample

case class AnonymousAudienceChat(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = AnonymousAudienceChat.`type`
}

object AnonymousAudienceChat {
  val `type`: String = "AnonymousAudienceChatFromClient"
}
