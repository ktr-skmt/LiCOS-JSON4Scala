package engine.village.example.client2server

import engine.ClientToServerVillageExample

case class OnymousAudienceScroll(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Scroll.`type`
}

object OnymousAudienceScroll {
  val `type`: String = "OnymousAudienceScroll"
}
