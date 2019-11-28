package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedFlavorTextMessageProtocol
import licos.protocol.engine.analysis.village.client2server.ReceivedFlavorTextMessageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.ReceivedFlavorTextMessage
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

class ReceivedFlavorTextMessageAE extends ReceivedFlavorTextMessageAnalysisEngine {
  override def process(
      box:                       VillageBOX,
      receivedFlavorTextMessage: ReceivedFlavorTextMessageProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(ReceivedFlavorTextMessage.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
