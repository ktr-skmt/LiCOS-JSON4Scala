package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.AnonymousAudienceChatFromClientProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait AnonymousAudienceChatFromClientAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(
      box:                             VillageBOX,
      anonymousAudienceChatFromClient: AnonymousAudienceChatFromClientProtocol
  ): Try[VillageMessageProtocol]
}

object AnonymousAudienceChatFromClientAnalysisEngine {
  val name: String = "village.client2server.AnonymousAudienceChatFromClient"
}
