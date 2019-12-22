package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ChatFromClientProtocol
import licos.protocol.engine.async.analysis.village.client2server.ChatFromClientAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.ChatFromClient
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class ChatFromClientAE extends ChatFromClientAnalysisEngine {
  override def process(box: VillageBOX, chatFromClient: ChatFromClientProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(ChatFromClient.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
