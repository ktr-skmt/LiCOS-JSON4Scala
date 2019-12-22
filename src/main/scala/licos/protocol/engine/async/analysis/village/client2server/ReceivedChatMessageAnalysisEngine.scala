package licos.protocol.engine.async.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedChatMessageProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait ReceivedChatMessageAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: VillageBOX, receivedChatMessage: ReceivedChatMessageProtocol)(
      implicit ec: ExecutionContext
  ): Future[VillageMessageProtocol]
}

object ReceivedChatMessageAnalysisEngine {
  val name: String = "village.client2server.ReceivedChatMessage"
}
