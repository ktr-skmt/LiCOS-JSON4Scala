package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.BoardProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait BoardAnalysisEngine extends VillageAnalysisEngine {
  def process(box: VillageBOX, board: BoardProtocol): Try[VillageMessageProtocol]
}

object BoardAnalysisEngine {
  val name: String = "village.client2server.BoardAnalysisEngine"
}