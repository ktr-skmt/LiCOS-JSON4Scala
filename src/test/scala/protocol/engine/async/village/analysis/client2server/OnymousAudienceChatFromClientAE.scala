package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.OnymousAudienceChatFromClientProtocol
import licos.protocol.engine.async.analysis.village.client2server.OnymousAudienceChatFromClientAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.OnymousAudienceChatFromClient
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class OnymousAudienceChatFromClientAE extends OnymousAudienceChatFromClientAnalysisEngine {
  override def process(
      box:                           VillageBOX,
      onymousAudienceChatFromClient: OnymousAudienceChatFromClientProtocol
  )(implicit ec:                     ExecutionContext): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(OnymousAudienceChatFromClient.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
