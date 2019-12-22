package licos.protocol.engine.async.analysis.village.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.MorningPhaseProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait MorningPhaseAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: VillageBOX, morningPhase: MorningPhaseProtocol)(
      implicit ec: ExecutionContext
  ): Future[VillageMessageProtocol]
}

object MorningPhaseAnalysisEngine {
  val name: String = "village.server2client.MorningPhase"
}
