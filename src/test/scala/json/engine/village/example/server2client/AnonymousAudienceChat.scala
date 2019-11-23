package json.engine.village.example.server2client

import json.engine.ServerToClientVillageExample

case class AnonymousAudienceChat(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = AnonymousAudienceChat.`type`
}

object AnonymousAudienceChat {
  val `type`: String = "AnonymousAudienceChatFromServer"
}
