package licos.protocol.engine.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.ScrollProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait ScrollAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, scroll: ScrollProtocol): Try[VillageMessageProtocol]
}

object ScrollAnalysisEngine {
  val name: String = "village.client2server.server2logger.Scroll"
}