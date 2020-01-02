package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.OnymousAudienceChatFromServerProtocol
import licos.protocol.engine.async.analysis.village.server2client.OnymousAudienceChatFromServerAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.OnymousAudienceChatFromServer
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class OnymousAudienceChatFromServerAE extends OnymousAudienceChatFromServerAnalysisEngine {
  override def process(
      box:                           VillageBOX,
      onymousAudienceChatFromServer: OnymousAudienceChatFromServerProtocol
  )(implicit ec:                     ExecutionContext): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(OnymousAudienceChatFromServer.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
