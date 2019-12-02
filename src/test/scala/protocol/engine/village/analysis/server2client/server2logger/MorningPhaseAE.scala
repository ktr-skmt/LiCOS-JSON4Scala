package protocol.engine.village.analysis.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.MorningPhaseProtocol
import licos.protocol.engine.analysis.village.server2client.server2logger.MorningPhaseAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.server2logger.MorningPhase
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