package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class OnymousAudienceBoard(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = OnymousAudienceBoard.`type`
}

object OnymousAudienceBoard {
  val `type`: String = "village.client2server.OnymousAudienceBoard"
}
