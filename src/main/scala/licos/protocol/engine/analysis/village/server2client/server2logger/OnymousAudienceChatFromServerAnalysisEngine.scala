package licos.protocol.engine.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.OnymousAudienceChatFromServerProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait OnymousAudienceChatFromServerAnalysisEngine extends VillageAnalysisEngine {
  def process(box:                           VillageBOX,
              onymousAudienceChatFromServer: OnymousAudienceChatFromServerProtocol): Try[VillageMessageProtocol]
}

object OnymousAudienceChatFromServerAnalysisEngine {
  val name: String = "village.server2client.server2logger.OnymousAudienceChatFromServer"
}