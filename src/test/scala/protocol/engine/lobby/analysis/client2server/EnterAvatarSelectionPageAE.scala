package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.EnterAvatarSelectionPageProtocol
import licos.protocol.engine.analysis.lobby.client2server.EnterAvatarSelectionPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.EnterAvatarSelectionPage

import scala.util.{Failure, Success, Try}

final class EnterAvatarSelectionPageAE extends EnterAvatarSelectionPageAnalysisEngine {
  override def process(
      box:                              LobbyBOX,
      enterAvatarSelectionPageProtocol: EnterAvatarSelectionPageProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(EnterAvatarSelectionPage.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
