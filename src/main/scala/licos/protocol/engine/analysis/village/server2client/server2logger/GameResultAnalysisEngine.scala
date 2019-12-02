package licos.protocol.engine.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.GameResultProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait GameResultAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, gameResult: GameResultProtocol): Try[VillageMessageProtocol]
}

object GameResultAnalysisEngine {
  val name: String = "village.server2client.server2logger.GameResult"
}
