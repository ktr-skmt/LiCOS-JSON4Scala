package licos.protocol.engine.analysis.village.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.AnonymousAudienceChatFromServerProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait AnonymousAudienceChatFromServerAnalysisEngine extends VillageAnalysisEngine {
  def process(box: VillageBOX, anonymousAudienceChatFromServer: AnonymousAudienceChatFromServerProtocol): Try[VillageMessageProtocol]
}

object AnonymousAudienceChatFromServerAnalysisEngine {
  val name: String = "village.server2client.AnonymousAudienceChatFromServer"
}