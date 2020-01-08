package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.EnterAvatarSelectionPageProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait EnterAvatarSelectionPageAnalysisEngine {
  def process(
      box:                              LobbyBOX,
      enterAvatarSelectionPageProtocol: EnterAvatarSelectionPageProtocol
  ): Try[LobbyMessageProtocol]
}

object EnterAvatarSelectionPageAnalysisEngine {
  val name: String = "lobby.client2server.EnterAvatarSelectionPage"
}
