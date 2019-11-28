package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class OnymousAudienceScroll(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = OnymousAudienceScroll.`type`
}

object OnymousAudienceScroll {
  val `type`: String = "village.client2server.OnymousAudienceScroll"
}
