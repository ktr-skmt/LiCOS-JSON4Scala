package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedFlavorTextMessageProtocol
import licos.protocol.engine.async.analysis.village.client2server.ReceivedFlavorTextMessageAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.ReceivedFlavorTextMessage
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class ReceivedFlavorTextMessageAE extends ReceivedFlavorTextMessageAnalysisEngine {
  override def process(
      box:                       VillageBOX,
      receivedFlavorTextMessage: ReceivedFlavorTextMessageProtocol
  )(implicit ec:                 ExecutionContext): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(ReceivedFlavorTextMessage.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
