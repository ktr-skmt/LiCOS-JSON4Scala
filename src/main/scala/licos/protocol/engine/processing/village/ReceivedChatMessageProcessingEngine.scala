package licos.protocol.engine.processing.village

import licos.json.element.village.receipt.JsonReceivedChatMessage
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedChatMessageProtocol
import licos.protocol.engine.analysis.village.client2server.ReceivedChatMessageAnalysisEngine
import licos.protocol.engine.processing.{JSON2ProtocolException, VillageBOX}

import scala.util.{Failure, Try}

object ReceivedChatMessageProcessingEngine {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(
      box:    VillageBOX,
      engine: ReceivedChatMessageAnalysisEngine,
      json:   JsonReceivedChatMessage
  ): Try[VillageMessageProtocol] = {
    ReceivedChatMessageProtocol.read(json) match {
      case Some(protocol) =>
        engine.process(box, protocol)
      case None => Failure(new JSON2ProtocolException(ReceivedChatMessageAnalysisEngine.name))
    }
  }
}
