package licos.protocol.engine.processing.village

import licos.json.element.village.receipt.JsonReceivedFlavorTextMessage
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedFlavorTextMessageProtocol
import licos.protocol.engine.analysis.village.client2server.ReceivedFlavorTextMessageAnalysisEngine
import licos.protocol.engine.processing.{JSON2ProtocolException, VillageBOX}

import scala.util.{Failure, Try}

object ReceivedFlavorTextMessageProcessingEngine {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(
      box:    VillageBOX,
      engine: ReceivedFlavorTextMessageAnalysisEngine,
      json:   JsonReceivedFlavorTextMessage
  ): Try[VillageMessageProtocol] = {
    ReceivedFlavorTextMessageProtocol.read(json) match {
      case Some(protocol) =>
        engine.process(box, protocol)
      case None => Failure(new JSON2ProtocolException(ReceivedFlavorTextMessageAnalysisEngine.name))
    }
  }
}
