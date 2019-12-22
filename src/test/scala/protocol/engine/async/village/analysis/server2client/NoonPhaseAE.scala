package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NoonPhaseProtocol
import licos.protocol.engine.async.analysis.village.server2client.NoonPhaseAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.NoonPhase
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class NoonPhaseAE extends NoonPhaseAnalysisEngine {
  override def process(box: VillageBOX, noonPhase: NoonPhaseProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(NoonPhase.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
