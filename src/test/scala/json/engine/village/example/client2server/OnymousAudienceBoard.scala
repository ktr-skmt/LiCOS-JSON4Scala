package json.engine.village.example.client2server

import json.engine.ClientToServerVillageExample

final case class OnymousAudienceBoard(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = OnymousAudienceBoard.`type`
}

object OnymousAudienceBoard {
  val `type`: String = "OnymousAudienceBoard"
}
