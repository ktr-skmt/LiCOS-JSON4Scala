package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationProtocol
import licos.protocol.engine.analysis.village.server2client.NextGameInvitationAnalysisEngine
import licos.protocol.engine.processing.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.NextGameInvitation
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

class NextGameInvitationAE extends NextGameInvitationAnalysisEngine {
  override def process(box: VillageBOX, nextGameInvitation: NextGameInvitationProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(NextGameInvitation.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
