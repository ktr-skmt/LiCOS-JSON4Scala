package protocol.engine.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.OnymousAudienceSelectionPageProtocol
import licos.protocol.engine.analysis.lobby.server2client.OnymousAudienceSelectionPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.server2client.OnymousAudienceSelectionPage

import scala.util.{Failure, Success, Try}

final class OnymousAudienceSelectionPageAE extends OnymousAudienceSelectionPageAnalysisEngine {
  override def process(
      box:                                  LobbyBOX,
      onymousAudienceSelectionPageProtocol: OnymousAudienceSelectionPageProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(OnymousAudienceSelectionPage.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
