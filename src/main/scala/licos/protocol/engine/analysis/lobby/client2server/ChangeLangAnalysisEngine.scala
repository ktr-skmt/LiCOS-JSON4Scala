package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeLangProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait ChangeLangAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, changeLangProtocol: ChangeLangProtocol): Try[LobbyMessageProtocol]
}

object ChangeLangAnalysisEngine {
  val name: String = "lobby.client2server.ChangeLang"
}
