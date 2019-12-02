package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.AnonymousAudienceChatFromServerProtocol
import licos.protocol.engine.analysis.village.server2client.AnonymousAudienceChatFromServerAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.AnonymousAudienceChatFromServer
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class AnonymousAudienceChatFromServerAE extends AnonymousAudienceChatFromServerAnalysisEngine {
  override def process(
      box:                             VillageBOX,
      anonymousAudienceChatFromServer: AnonymousAudienceChatFromServerProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(AnonymousAudienceChatFromServer.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
