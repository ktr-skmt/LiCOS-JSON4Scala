package licos.protocol.engine.async.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.LeaveWaitingPageProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait LeaveWaitingPageAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: VillageBOX, leaveWaitingPage: LeaveWaitingPageProtocol)(
      implicit ec: ExecutionContext
  ): Future[VillageMessageProtocol]
}

object LeaveWaitingPageAnalysisEngine {
  val name: String = "village.client2server.LeaveWaitingPage"
}
