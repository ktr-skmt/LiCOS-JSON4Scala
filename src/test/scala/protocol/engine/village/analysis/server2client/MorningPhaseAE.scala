package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.MorningPhaseProtocol
import licos.protocol.engine.analysis.village.server2client.MorningPhaseAnalysisEngine
import licos.protocol.engine.processing.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.MorningPhase
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

class MorningPhaseAE extends MorningPhaseAnalysisEngine {
  override def process(box: VillageBOX, morningPhase: MorningPhaseProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(MorningPhase.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
