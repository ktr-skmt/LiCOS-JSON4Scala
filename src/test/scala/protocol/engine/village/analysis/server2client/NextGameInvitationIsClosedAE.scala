package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationIsClosedProtocol
import licos.protocol.engine.analysis.village.server2client.NextGameInvitationIsClosedAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.NextGameInvitationIsClosed
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class NextGameInvitationIsClosedAE extends NextGameInvitationIsClosedAnalysisEngine {
  override def process(
      box:                        VillageBOX,
      nextGameInvitationIsClosed: NextGameInvitationIsClosedProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(NextGameInvitationIsClosed.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
