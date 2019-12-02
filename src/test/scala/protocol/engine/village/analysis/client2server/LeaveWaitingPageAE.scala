package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.LeaveWaitingPageProtocol
import licos.protocol.engine.analysis.village.client2server.LeaveWaitingPageAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.LeaveWaitingPage
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class LeaveWaitingPageAE extends LeaveWaitingPageAnalysisEngine {
  override def process(
      box:                      VillageBOX,
      leaveWaitingPageProtocol: LeaveWaitingPageProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(LeaveWaitingPage.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
