package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.OnymousAudienceBoardProtocol
import licos.protocol.engine.async.analysis.village.client2server.OnymousAudienceBoardAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.OnymousAudienceBoard
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class OnymousAudienceBoardAE extends OnymousAudienceBoardAnalysisEngine {
  override def process(
      box:                  VillageBOX,
      onymousAudienceBoard: OnymousAudienceBoardProtocol
  )(implicit ec:            ExecutionContext): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(OnymousAudienceBoard.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
