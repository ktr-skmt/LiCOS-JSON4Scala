package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.VoteProtocol
import licos.protocol.engine.analysis.village.client2server.VoteAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.Vote
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

final class VoteAE extends VoteAnalysisEngine {
  override def process(box: VillageBOX, vote: VoteProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(Vote.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
