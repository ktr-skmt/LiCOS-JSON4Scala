package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.LeaveWaitingPageProtocol
import licos.protocol.engine.async.analysis.village.client2server.LeaveWaitingPageAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.LeaveWaitingPage
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class LeaveWaitingPageAE extends LeaveWaitingPageAnalysisEngine {
  override def process(
      box:                      VillageBOX,
      leaveWaitingPageProtocol: LeaveWaitingPageProtocol
  )(implicit ec:                ExecutionContext): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(LeaveWaitingPage.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
