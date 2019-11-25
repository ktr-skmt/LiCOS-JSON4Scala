package json.engine.village.example.server2client

import json.engine.ServerToClientVillageExample

final case class OnymousAudienceChat(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = OnymousAudienceChat.`type`
}

object OnymousAudienceChat {
  val `type`: String = "OnymousAudienceChatFromServer"
}
