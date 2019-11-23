package licos.protocol.engine.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.NightPhaseProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait NightPhaseAnalysisEngine extends VillageAnalysisEngine {
  def process(box: VillageBOX, nightPhase: NightPhaseProtocol): Try[VillageMessageProtocol]
}

object NightPhaseAnalysisEngine {
  val name: String = "village.server2client.server2logger.NightPhase"
}