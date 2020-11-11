package licos.protocol.engine.async.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.OnymousAudienceBoardProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait OnymousAudienceBoardAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: VillageBOX, onymousAudienceBoard: OnymousAudienceBoardProtocol)(implicit
      ec:          ExecutionContext
  ): Future[VillageMessageProtocol]
}

object OnymousAudienceBoardAnalysisEngine {
  val name: String = "village.client2server.server2logger.OnymousAudienceBoard"
}
