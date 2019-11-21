package licos.protocol.engine.analysis.village.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait NextGameInvitationAnalysisEngine extends VillageAnalysisEngine {
  def process(box: VillageBOX, nextGameInvitation: NextGameInvitationProtocol): Try[VillageMessageProtocol]
}

object NextGameInvitationAnalysisEngine {
  val name: String = "village.server2client.NextGameInvitation"
}