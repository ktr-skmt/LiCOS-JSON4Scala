package protocol.engine.village.analysis.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.VoteProtocol
import licos.protocol.engine.analysis.village.client2server.server2logger.VoteAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.server2logger.Vote
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class VoteAE extends VoteAnalysisEngine {
  override def process(box: VillageBOX, vote: VoteProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(Vote.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
