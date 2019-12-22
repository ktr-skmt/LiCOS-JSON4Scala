package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationIsClosedProtocol
import licos.protocol.engine.async.analysis.village.server2client.NextGameInvitationIsClosedAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.NextGameInvitationIsClosed
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class NextGameInvitationIsClosedAE extends NextGameInvitationIsClosedAnalysisEngine {
  override def process(
      box:                        VillageBOX,
      nextGameInvitationIsClosed: NextGameInvitationIsClosedProtocol
  )(implicit ec:                  ExecutionContext): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(NextGameInvitationIsClosed.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
