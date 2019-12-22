package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.AnonymousAudienceChatFromClientProtocol
import licos.protocol.engine.async.analysis.village.client2server.AnonymousAudienceChatFromClientAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.AnonymousAudienceChatFromClient
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class AnonymousAudienceChatFromClientAE extends AnonymousAudienceChatFromClientAnalysisEngine {
  override def process(
      box:                             VillageBOX,
      anonymousAudienceChatFromClient: AnonymousAudienceChatFromClientProtocol
  )(implicit ec:                       ExecutionContext): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(AnonymousAudienceChatFromClient.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
