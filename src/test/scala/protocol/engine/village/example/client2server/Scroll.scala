package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class Scroll(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Scroll.`type`
}

object Scroll {
  val `type`: String = "village.client2server.Scroll"
}
