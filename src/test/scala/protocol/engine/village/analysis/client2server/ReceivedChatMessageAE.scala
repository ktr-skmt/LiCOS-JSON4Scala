package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedChatMessageProtocol
import licos.protocol.engine.analysis.village.client2server.ReceivedChatMessageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.ReceivedChatMessage
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

class ReceivedChatMessageAE extends ReceivedChatMessageAnalysisEngine {
  override def process(
      box:                 VillageBOX,
      receivedChatMessage: ReceivedChatMessageProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(ReceivedChatMessage.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
