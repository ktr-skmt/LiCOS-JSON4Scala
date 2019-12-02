package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.AnonymousAudienceChatFromClientProtocol
import licos.protocol.engine.analysis.village.client2server.AnonymousAudienceChatFromClientAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.AnonymousAudienceChatFromClient
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class AnonymousAudienceChatFromClientAE extends AnonymousAudienceChatFromClientAnalysisEngine {
  override def process(
      box:                             VillageBOX,
      anonymousAudienceChatFromClient: AnonymousAudienceChatFromClientProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(AnonymousAudienceChatFromClient.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
