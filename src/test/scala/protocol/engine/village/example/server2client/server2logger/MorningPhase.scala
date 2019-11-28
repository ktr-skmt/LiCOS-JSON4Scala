package protocol.engine.village.example.server2client.server2logger

import protocol.engine.ServerToClientVillageExample

final case class MorningPhase(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = MorningPhase.`type`
}

object MorningPhase {
  val `type`: String = "village.server2client.server2logger.MorningPhase"
}
