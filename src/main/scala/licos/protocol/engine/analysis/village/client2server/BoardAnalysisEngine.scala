package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.BoardProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait BoardAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, board: BoardProtocol): Try[VillageMessageProtocol]
}

object BoardAnalysisEngine {
  val name: String = "village.client2server.BoardAnalysisEngine"
}
