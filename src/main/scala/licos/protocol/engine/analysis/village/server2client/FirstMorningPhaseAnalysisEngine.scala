package licos.protocol.engine.analysis.village.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.FirstMorningPhaseProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait FirstMorningPhaseAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, firstMorningPhase: FirstMorningPhaseProtocol): Try[VillageMessageProtocol]
}

object FirstMorningPhaseAnalysisEngine {
  val name: String = "village.server2client.FirstMorningPhase"
}
