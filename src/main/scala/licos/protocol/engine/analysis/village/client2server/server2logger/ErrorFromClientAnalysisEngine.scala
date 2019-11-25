package licos.protocol.engine.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.ErrorFromClientProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait ErrorFromClientAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, errorFromClient: ErrorFromClientProtocol): Try[VillageMessageProtocol]
}

object ErrorFromClientAnalysisEngine {
  val name: String = "village.client2server.server2logger.ErrorFromClient"
}
