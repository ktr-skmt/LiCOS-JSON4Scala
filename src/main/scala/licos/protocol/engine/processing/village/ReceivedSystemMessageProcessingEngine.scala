package licos.protocol.engine.processing.village

import licos.json.element.village.receipt.JsonReceivedSystemMessage
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedSystemMessageProtocol
import licos.protocol.engine.analysis.village.client2server.ReceivedSystemMessageAnalysisEngine
import licos.protocol.engine.processing.{JSON2ProtocolException, VillageBOX}

import scala.util.{Failure, Try}

object ReceivedSystemMessageProcessingEngine {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(
      box:    VillageBOX,
      engine: ReceivedSystemMessageAnalysisEngine,
      json:   JsonReceivedSystemMessage
  ): Try[VillageMessageProtocol] = {
    ReceivedSystemMessageProtocol.read(json) match {
      case Some(protocol) =>
        engine.process(box, protocol)
      case None => Failure(new JSON2ProtocolException(ReceivedSystemMessageAnalysisEngine.name))
    }
  }
}
