package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.OnymousAudienceSelectionPageProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait OnymousAudienceSelectionPageAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(
      box:                                  LobbyBOX,
      onymousAudienceSelectionPageProtocol: OnymousAudienceSelectionPageProtocol
  ): Try[LobbyMessageProtocol]
}

object OnymousAudienceSelectionPageAnalysisEngine {
  val name: String = "lobby.server2client.OnymousAudienceSelectionPage"
}
