package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.FirstMorningPhaseProtocol
import licos.protocol.engine.async.analysis.village.server2client.FirstMorningPhaseAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.FirstMorningPhase
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class FirstMorningPhaseAE extends FirstMorningPhaseAnalysisEngine {
  override def process(box: VillageBOX, firstMorningPhase: FirstMorningPhaseProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(FirstMorningPhase.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
