package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ErrorFromClientProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait ErrorFromClientAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, errorFromClient: ErrorFromClientProtocol): Try[VillageMessageProtocol]
}

object ErrorFromClientAnalysisEngine {
  val name: String = "village.client2server.ErrorFromClient"
}
