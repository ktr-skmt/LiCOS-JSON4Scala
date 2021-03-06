package licos.protocol.engine.analysis.village.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.GameResultProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait GameResultAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, gameResult: GameResultProtocol): Try[VillageMessageProtocol]
}

object GameResultAnalysisEngine {
  val name: String = "village.server2client.GameResult"
}
