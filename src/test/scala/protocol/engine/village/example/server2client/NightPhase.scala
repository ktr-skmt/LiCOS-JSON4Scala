package protocol.engine.village.example.server2client

import protocol.engine.ServerToClientVillageExample

final case class NightPhase(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = NightPhase.`type`
}

object NightPhase {
  val `type`: String = "village.server2client.NightPhase"
}
