package engine.village.example.server2client

import engine.ServerToClientVillageExample

case class Phase(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = Phase.`type`
}

object Phase {
  val `type`: String = "Phase"
}
