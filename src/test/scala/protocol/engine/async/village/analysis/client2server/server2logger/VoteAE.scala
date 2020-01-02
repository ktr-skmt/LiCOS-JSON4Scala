package protocol.engine.async.village.analysis.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.VoteProtocol
import licos.protocol.engine.async.analysis.village.client2server.server2logger.VoteAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.server2logger.Vote
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class VoteAE extends VoteAnalysisEngine {
  override def process(box: VillageBOX, vote: VoteProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(Vote.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
