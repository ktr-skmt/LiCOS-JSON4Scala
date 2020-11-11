package licos.protocol.engine.async.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.ErrorFromClientProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait ErrorFromClientAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: VillageBOX, errorFromClient: ErrorFromClientProtocol)(implicit
      ec:          ExecutionContext
  ): Future[VillageMessageProtocol]
}

object ErrorFromClientAnalysisEngine {
  val name: String = "village.client2server.server2logger.ErrorFromClient"
}
