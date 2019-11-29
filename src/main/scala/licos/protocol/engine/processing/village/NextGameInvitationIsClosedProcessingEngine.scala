package licos.protocol.engine.processing.village

import licos.json.element.village.invite.JsonNextGameInvitationIsClosed
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationIsClosedProtocol
import licos.protocol.engine.analysis.village.server2client.NextGameInvitationIsClosedAnalysisEngine
import licos.protocol.engine.processing.{JSON2ProtocolException, VillageBOX}

import scala.util.{Failure, Try}

object NextGameInvitationIsClosedProcessingEngine {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(
      box:    VillageBOX,
      engine: NextGameInvitationIsClosedAnalysisEngine,
      json:   JsonNextGameInvitationIsClosed
  ): Try[VillageMessageProtocol] = {
    NextGameInvitationIsClosedProtocol.read(json) match {
      case Some(protocol) =>
        engine.process(box, protocol)
      case None => Failure(new JSON2ProtocolException(NextGameInvitationIsClosedAnalysisEngine.name))
    }
  }
}
