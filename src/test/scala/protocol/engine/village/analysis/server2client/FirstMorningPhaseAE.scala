package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.FirstMorningPhaseProtocol
import licos.protocol.engine.analysis.village.server2client.FirstMorningPhaseAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.FirstMorningPhase
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class FirstMorningPhaseAE extends FirstMorningPhaseAnalysisEngine {
  override def process(box: VillageBOX, firstMorningPhase: FirstMorningPhaseProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(FirstMorningPhase.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
