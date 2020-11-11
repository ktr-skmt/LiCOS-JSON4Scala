package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NightPhaseProtocol
import licos.protocol.engine.async.analysis.village.server2client.NightPhaseAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.NightPhase
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class NightPhaseAE extends NightPhaseAnalysisEngine {
  override def process(box: VillageBOX, nightPhase: NightPhaseProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(NightPhase.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
