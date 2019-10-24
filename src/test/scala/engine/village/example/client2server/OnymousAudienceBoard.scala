package engine.village.example.client2server

import engine.ClientToServerVillageExample

case class OnymousAudienceBoard(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = OnymousAudienceBoard.`type`
}

object OnymousAudienceBoard {
  val `type`: String = "OnymousAudienceBoard"
}