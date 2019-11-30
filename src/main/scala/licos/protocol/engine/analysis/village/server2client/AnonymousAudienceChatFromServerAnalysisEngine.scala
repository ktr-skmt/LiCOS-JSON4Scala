package licos.protocol.engine.analysis.village.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.AnonymousAudienceChatFromServerProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait AnonymousAudienceChatFromServerAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(
      box:                             VillageBOX,
      anonymousAudienceChatFromServer: AnonymousAudienceChatFromServerProtocol
  ): Try[VillageMessageProtocol]
}

object AnonymousAudienceChatFromServerAnalysisEngine {
  val name: String = "village.server2client.AnonymousAudienceChatFromServer"
}
