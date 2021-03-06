package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.OnymousAudienceChatFromClientProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait OnymousAudienceChatFromClientAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(
      box:                           VillageBOX,
      onymousAudienceChatFromClient: OnymousAudienceChatFromClientProtocol
  ): Try[VillageMessageProtocol]
}

object OnymousAudienceChatFromClientAnalysisEngine {
  val name: String = "village.client2server.OnymousAudienceChatFromClient"
}
