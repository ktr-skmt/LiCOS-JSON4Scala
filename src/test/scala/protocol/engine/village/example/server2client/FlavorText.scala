package protocol.engine.village.example.server2client

import protocol.engine.ServerToClientVillageExample

final case class FlavorText(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = FlavorText.`type`
}

object FlavorText {
  val `type`: String = "village.server2client.FlavorText"
}
