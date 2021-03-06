package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ScrollProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait ScrollAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, scroll: ScrollProtocol): Try[VillageMessageProtocol]
}

object ScrollAnalysisEngine {
  val name: String = "village.client2server.Scroll"
}
