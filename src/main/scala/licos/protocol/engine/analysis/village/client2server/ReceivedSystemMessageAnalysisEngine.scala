package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedSystemMessageProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait ReceivedSystemMessageAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, receivedSystemMessage: ReceivedSystemMessageProtocol): Try[VillageMessageProtocol]
}

object ReceivedSystemMessageAnalysisEngine {
  val name: String = "village.client2server.ReceivedSystemMessage"
}