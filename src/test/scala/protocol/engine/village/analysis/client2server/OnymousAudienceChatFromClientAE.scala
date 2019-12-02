package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.OnymousAudienceChatFromClientProtocol
import licos.protocol.engine.analysis.village.client2server.OnymousAudienceChatFromClientAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.OnymousAudienceChatFromClient
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class OnymousAudienceChatFromClientAE extends OnymousAudienceChatFromClientAnalysisEngine {
  override def process(
      box:                           VillageBOX,
      onymousAudienceChatFromClient: OnymousAudienceChatFromClientProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(OnymousAudienceChatFromClient.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
