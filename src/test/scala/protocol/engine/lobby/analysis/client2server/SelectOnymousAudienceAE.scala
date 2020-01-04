package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.SelectOnymousAudienceProtocol
import licos.protocol.engine.analysis.lobby.client2server.SelectOnymousAudienceAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.SelectOnymousAudience

import scala.util.{Failure, Success, Try}

final class SelectOnymousAudienceAE extends SelectOnymousAudienceAnalysisEngine {
  override def process(
      box:                           LobbyBOX,
      selectOnymousAudienceProtocol: SelectOnymousAudienceProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(SelectOnymousAudience.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
