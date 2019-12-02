package protocol.engine.village.example.server2client.server2logger

import protocol.engine.ServerToLoggerVillageExample

final case class MorningPhase(filePath: String) extends ServerToLoggerVillageExample(filePath) {
  override val `type`: String = MorningPhase.`type`
}

object MorningPhase {
  val `type`: String = "village.server2client.server2logger.MorningPhase"
}
