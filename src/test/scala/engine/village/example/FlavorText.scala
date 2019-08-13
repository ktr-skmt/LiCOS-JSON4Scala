package engine.village.example

import engine.ServerToClientVillageExample

case class FlavorText(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = FlavorText.`type`
}

object FlavorText {
  val `type`: String = "FlavorText"
}