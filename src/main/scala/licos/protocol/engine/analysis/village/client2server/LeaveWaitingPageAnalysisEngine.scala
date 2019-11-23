package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.LeaveWaitingPageProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait LeaveWaitingPageAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, leaveWaitingPage: LeaveWaitingPageProtocol): Try[VillageMessageProtocol]
}

object LeaveWaitingPageAnalysisEngine {
  val name: String = "village.client2server.LeaveWaitingPage"
}
