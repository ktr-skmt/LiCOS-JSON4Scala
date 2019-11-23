package licos.protocol.engine.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.ChatFromClientProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait ChatFromClientAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, chatFromClient: ChatFromClientProtocol): Try[VillageMessageProtocol]
}

object ChatFromClientAnalysisEngine {
  val name: String = "village.client2server.server2logger.ChatFromClient"
}