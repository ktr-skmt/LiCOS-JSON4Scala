package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.OnymousAudienceBoardProtocol
import licos.protocol.engine.analysis.village.client2server.OnymousAudienceBoardAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.OnymousAudienceBoard
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class OnymousAudienceBoardAE extends OnymousAudienceBoardAnalysisEngine {
  override def process(
      box:                  VillageBOX,
      onymousAudienceBoard: OnymousAudienceBoardProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(OnymousAudienceBoard.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
