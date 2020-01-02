package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.ChatFromServerProtocol
import licos.protocol.engine.async.analysis.village.server2client.ChatFromServerAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.ChatFromServer
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class ChatFromServerAE extends ChatFromServerAnalysisEngine {
  override def process(box: VillageBOX, chatFromServer: ChatFromServerProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(ChatFromServer.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
