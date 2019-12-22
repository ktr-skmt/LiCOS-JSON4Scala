package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedChatMessageProtocol
import licos.protocol.engine.async.analysis.village.client2server.ReceivedChatMessageAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.ReceivedChatMessage
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class ReceivedChatMessageAE extends ReceivedChatMessageAnalysisEngine {
  override def process(
      box:                 VillageBOX,
      receivedChatMessage: ReceivedChatMessageProtocol
  )(implicit ec:           ExecutionContext): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(ReceivedChatMessage.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
