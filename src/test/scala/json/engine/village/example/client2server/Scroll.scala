package json.engine.village.example.client2server

import json.engine.ClientToServerVillageExample

case class Scroll(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Scroll.`type`
}

object Scroll {
  val `type`: String = "Scroll"
}
