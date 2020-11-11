package licos.protocol.engine.async.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.ChatFromClientProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait ChatFromClientAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: VillageBOX, chatFromClient: ChatFromClientProtocol)(implicit
      ec:          ExecutionContext
  ): Future[VillageMessageProtocol]
}

object ChatFromClientAnalysisEngine {
  val name: String = "village.client2server.server2logger.ChatFromClient"
}
