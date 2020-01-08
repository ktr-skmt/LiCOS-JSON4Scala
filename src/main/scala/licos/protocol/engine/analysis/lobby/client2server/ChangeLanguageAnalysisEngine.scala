package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeLanguageProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait ChangeLanguageAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, changeLanguageProtocol: ChangeLanguageProtocol): Try[LobbyMessageProtocol]
}

object ChangeLanguageAnalysisEngine {
  val name: String = "lobby.client2server.ChangeLanguage"
}
