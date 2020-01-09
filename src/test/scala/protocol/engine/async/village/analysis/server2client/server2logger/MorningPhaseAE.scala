package protocol.engine.async.village.analysis.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.MorningPhaseProtocol
import licos.protocol.engine.async.analysis.village.server2client.server2logger.MorningPhaseAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.server2logger.MorningPhase
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class MorningPhaseAE extends MorningPhaseAnalysisEngine {
  override def process(box: VillageBOX, morningPhase: MorningPhaseProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(MorningPhase.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
