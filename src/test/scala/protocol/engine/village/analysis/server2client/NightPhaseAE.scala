package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NightPhaseProtocol
import licos.protocol.engine.analysis.village.server2client.NightPhaseAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.NightPhase
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

final class NightPhaseAE extends NightPhaseAnalysisEngine {
  override def process(box: VillageBOX, nightPhase: NightPhaseProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(NightPhase.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
