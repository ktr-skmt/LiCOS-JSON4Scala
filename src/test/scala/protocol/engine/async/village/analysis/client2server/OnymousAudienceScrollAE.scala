package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.OnymousAudienceScrollProtocol
import licos.protocol.engine.async.analysis.village.client2server.OnymousAudienceScrollAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.OnymousAudienceScroll
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class OnymousAudienceScrollAE extends OnymousAudienceScrollAnalysisEngine {
  override def process(
      box:                   VillageBOX,
      onymousAudienceScroll: OnymousAudienceScrollProtocol
  )(implicit ec:             ExecutionContext): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(OnymousAudienceScroll.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
