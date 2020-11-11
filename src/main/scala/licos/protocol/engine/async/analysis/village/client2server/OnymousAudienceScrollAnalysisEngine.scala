package licos.protocol.engine.async.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.OnymousAudienceScrollProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait OnymousAudienceScrollAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: VillageBOX, onymousAudienceScroll: OnymousAudienceScrollProtocol)(implicit
      ec:          ExecutionContext
  ): Future[VillageMessageProtocol]
}

object OnymousAudienceScrollAnalysisEngine {
  val name: String = "village.client2server.OnymousAudienceScroll"
}
