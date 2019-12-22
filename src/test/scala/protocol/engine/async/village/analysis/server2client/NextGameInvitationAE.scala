package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationProtocol
import licos.protocol.engine.async.analysis.village.server2client.NextGameInvitationAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.NextGameInvitation
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class NextGameInvitationAE extends NextGameInvitationAnalysisEngine {
  override def process(box: VillageBOX, nextGameInvitation: NextGameInvitationProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(NextGameInvitation.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
