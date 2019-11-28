package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.AnonymousAudienceChatFromClientProtocol
import licos.protocol.engine.analysis.village.client2server.AnonymousAudienceChatFromClientAnalysisEngine
import licos.protocol.engine.processing.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.AnonymousAudienceChatFromClient
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

class AnonymousAudienceChatFromClientAE extends AnonymousAudienceChatFromClientAnalysisEngine {
  override def process(
      box:                             VillageBOX,
      anonymousAudienceChatFromClient: AnonymousAudienceChatFromClientProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(AnonymousAudienceChatFromClient.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
