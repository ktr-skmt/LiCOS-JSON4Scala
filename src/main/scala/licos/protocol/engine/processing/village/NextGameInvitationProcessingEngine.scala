package licos.protocol.engine.processing.village

import licos.json.element.village.invite.JsonNextGameInvitation
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationProtocol
import licos.protocol.engine.analysis.village.server2client.NextGameInvitationAnalysisEngine
import licos.protocol.engine.processing.{JSON2ProtocolException, VillageBOX}

import scala.util.{Failure, Try}

object NextGameInvitationProcessingEngine {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(
      box:    VillageBOX,
      engine: NextGameInvitationAnalysisEngine,
      json:   JsonNextGameInvitation
  ): Try[VillageMessageProtocol] = {
    NextGameInvitationProtocol.read(json) match {
      case Some(protocol) =>
        engine.process(box, protocol)
      case None => Failure(new JSON2ProtocolException(NextGameInvitationAnalysisEngine.name))
    }
  }
}
