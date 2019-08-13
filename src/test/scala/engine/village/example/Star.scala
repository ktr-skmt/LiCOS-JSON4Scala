package engine.village.example

import engine.ClientToServerVillageExample

case class Star(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Star.`type`
}

object Star {
  val `type`: String = "Star"
}