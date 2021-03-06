package licos.protocol.engine.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.VoteProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait VoteAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, vote: VoteProtocol): Try[VillageMessageProtocol]
}

object VoteAnalysisEngine {
  val name: String = "village.client2server.server2logger.Vote"
}
