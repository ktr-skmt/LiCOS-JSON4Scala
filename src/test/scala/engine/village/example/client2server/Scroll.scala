package engine.village.example.client2server

import engine.ClientToServerVillageExample

case class Scroll(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Scroll.`type`
}

object Scroll {
  val `type`: String = "Scroll"
}