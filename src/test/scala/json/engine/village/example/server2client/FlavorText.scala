package json.engine.village.example.server2client

import json.engine.ServerToClientVillageExample

case class FlavorText(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = FlavorText.`type`
}

object FlavorText {
  val `type`: String = "FlavorText"
}
