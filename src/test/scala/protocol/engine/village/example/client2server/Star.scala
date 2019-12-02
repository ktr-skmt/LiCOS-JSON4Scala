package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class Star(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Star.`type`
}

object Star {
  val `type`: String = "village.client2server.Star"
}
