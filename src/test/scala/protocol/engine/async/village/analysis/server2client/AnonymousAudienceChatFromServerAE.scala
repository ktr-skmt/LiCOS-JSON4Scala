package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.AnonymousAudienceChatFromServerProtocol
import licos.protocol.engine.async.analysis.village.server2client.AnonymousAudienceChatFromServerAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.AnonymousAudienceChatFromServer
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class AnonymousAudienceChatFromServerAE extends AnonymousAudienceChatFromServerAnalysisEngine {
  override def process(
      box:                             VillageBOX,
      anonymousAudienceChatFromServer: AnonymousAudienceChatFromServerProtocol
  )(implicit ec:                       ExecutionContext): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(AnonymousAudienceChatFromServer.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
