package protocol.engine.village.example.server2client

import protocol.engine.ServerToClientVillageExample

final case class NoonPhase(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = NoonPhase.`type`
}

object NoonPhase {
  val `type`: String = "village.server2client.NoonPhase"
}
