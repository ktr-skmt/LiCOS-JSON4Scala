package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedSystemMessageProtocol
import licos.protocol.engine.analysis.village.client2server.ReceivedSystemMessageAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.ReceivedSystemMessage
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class ReceivedSystemMessageAE extends ReceivedSystemMessageAnalysisEngine {
  override def process(
      box:                   VillageBOX,
      receivedSystemMessage: ReceivedSystemMessageProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(ReceivedSystemMessage.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
