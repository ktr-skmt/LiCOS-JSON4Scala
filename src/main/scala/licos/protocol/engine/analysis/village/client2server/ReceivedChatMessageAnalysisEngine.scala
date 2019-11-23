package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedChatMessageProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait ReceivedChatMessageAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, receivedChatMessage: ReceivedChatMessageProtocol): Try[VillageMessageProtocol]
}

object ReceivedChatMessageAnalysisEngine {
  val name: String = "village.client2server.ReceivedChatMessage"
}