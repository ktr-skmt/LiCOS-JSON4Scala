package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NoonPhaseProtocol
import licos.protocol.engine.analysis.village.server2client.NoonPhaseAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.NoonPhase
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

final class NoonPhaseAE extends NoonPhaseAnalysisEngine {
  override def process(box: VillageBOX, noonPhase: NoonPhaseProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(NoonPhase.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
