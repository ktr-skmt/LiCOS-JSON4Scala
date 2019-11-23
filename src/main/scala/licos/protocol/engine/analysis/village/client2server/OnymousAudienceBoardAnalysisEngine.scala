package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.OnymousAudienceBoardProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait OnymousAudienceBoardAnalysisEngine extends VillageAnalysisEngine {
  def process(box: VillageBOX, onymousAudienceBoard: OnymousAudienceBoardProtocol): Try[VillageMessageProtocol]
}

object OnymousAudienceBoardAnalysisEngine {
  val name: String = "village.client2server.OnymousAudienceBoard"
}