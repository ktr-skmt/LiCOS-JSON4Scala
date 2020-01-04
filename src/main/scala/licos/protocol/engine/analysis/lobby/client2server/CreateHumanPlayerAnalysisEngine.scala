package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateHumanPlayerProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait CreateHumanPlayerAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, createHumanPlayerProtocol: CreateHumanPlayerProtocol): Try[LobbyMessageProtocol]
}

object CreateHumanPlayerAnalysisEngine {
  val name: String = "lobby.client2server.CreateHumanPlayer"
}
