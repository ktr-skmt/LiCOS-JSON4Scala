package licos.protocol.engine.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.NoonPhaseProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait NoonPhaseAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, noonPhase: NoonPhaseProtocol): Try[VillageMessageProtocol]
}

object NoonPhaseAnalysisEngine {
  val name: String = "village.server2client.server2logger.NoonPhase"
}
