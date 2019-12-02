package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserEmailProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait ChangeUserEmailAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, changeUserEmailProtocol: ChangeUserEmailProtocol): Try[LobbyMessageProtocol]
}

object ChangeUserEmailAnalysisEngine {
  val name: String = "lobby.client2server.ChangeUserEmail"
}
