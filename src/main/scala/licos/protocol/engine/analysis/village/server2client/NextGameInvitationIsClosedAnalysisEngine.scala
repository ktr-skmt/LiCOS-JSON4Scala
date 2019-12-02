package licos.protocol.engine.analysis.village.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationIsClosedProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait NextGameInvitationIsClosedAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(
      box:                        VillageBOX,
      nextGameInvitationIsClosed: NextGameInvitationIsClosedProtocol
  ): Try[VillageMessageProtocol]
}

object NextGameInvitationIsClosedAnalysisEngine {
  val name: String = "village.server2client.NextGameInvitationIsClosed"
}
