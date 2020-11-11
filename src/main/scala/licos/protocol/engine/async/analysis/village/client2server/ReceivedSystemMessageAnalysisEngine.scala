package licos.protocol.engine.async.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedSystemMessageProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait ReceivedSystemMessageAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: VillageBOX, receivedSystemMessage: ReceivedSystemMessageProtocol)(implicit
      ec:          ExecutionContext
  ): Future[VillageMessageProtocol]
}

object ReceivedSystemMessageAnalysisEngine {
  val name: String = "village.client2server.ReceivedSystemMessage"
}
