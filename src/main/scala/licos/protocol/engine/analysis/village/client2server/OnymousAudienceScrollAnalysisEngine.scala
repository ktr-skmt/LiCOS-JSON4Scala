package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.OnymousAudienceScrollProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait OnymousAudienceScrollAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, onymousAudienceScroll: OnymousAudienceScrollProtocol): Try[VillageMessageProtocol]
}

object OnymousAudienceScrollAnalysisEngine {
  val name: String = "village.client2server.OnymousAudienceScroll"
}
