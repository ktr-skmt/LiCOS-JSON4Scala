package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReadyProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait ReadyAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, ready: ReadyProtocol): Try[VillageMessageProtocol]
}

object ReadyAnalysisEngine {
  val name: String = "village.client2server.Ready"
}
