package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedFlavorTextMessageProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait ReceivedFlavorTextMessageAnalysisEngine extends VillageAnalysisEngine {
  def process(box: VillageBOX, receivedFlavorTextMessage: ReceivedFlavorTextMessageProtocol): Try[VillageMessageProtocol]
}

object ReceivedFlavorTextMessageAnalysisEngine {
  val name: String = "village.client2server.ReceivedFlavorTextMessage"
}