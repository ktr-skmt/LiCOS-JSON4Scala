package licos.protocol.engine.processing.village

import licos.json.element.lobby.client2server.JsonLeaveWaitingPage
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.LeaveWaitingPageProtocol
import licos.protocol.engine.analysis.village.client2server.LeaveWaitingPageAnalysisEngine
import licos.protocol.engine.processing.{JSON2ProtocolException, VillageBOX}

import scala.util.{Failure, Try}

object LeaveWaitingPageProcessingEngine {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(
      box:    VillageBOX,
      engine: LeaveWaitingPageAnalysisEngine,
      json:   JsonLeaveWaitingPage
  ): Try[VillageMessageProtocol] = {
    LeaveWaitingPageProtocol.read(json) match {
      case Some(protocol) =>
        engine.process(box, protocol)
      case None => Failure(new JSON2ProtocolException(LeaveWaitingPageAnalysisEngine.name))
    }
  }
}
