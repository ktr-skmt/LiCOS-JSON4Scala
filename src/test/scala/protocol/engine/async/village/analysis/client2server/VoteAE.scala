package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.VoteProtocol
import licos.protocol.engine.async.analysis.village.client2server.VoteAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.Vote
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
