package licos.protocol.engine.analysis.village.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.MorningPhaseProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait MorningPhaseAnalysisEngine extends VillageAnalysisEngine {
  def process(box: VillageBOX, morningPhase: MorningPhaseProtocol): Try[VillageMessageProtocol]
}

object MorningPhaseAnalysisEngine {
  val name: String = "village.server2client.MorningPhase"
}