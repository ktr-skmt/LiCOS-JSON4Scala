package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.OnymousAudienceChatFromServerProtocol
import licos.protocol.engine.analysis.village.server2client.OnymousAudienceChatFromServerAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.OnymousAudienceChatFromServer
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class OnymousAudienceChatFromServerAE extends OnymousAudienceChatFromServerAnalysisEngine {
  override def process(
      box:                           VillageBOX,
      onymousAudienceChatFromServer: OnymousAudienceChatFromServerProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(OnymousAudienceChatFromServer.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
