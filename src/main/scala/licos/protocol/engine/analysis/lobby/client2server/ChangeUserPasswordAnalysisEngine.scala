package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserPasswordProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait ChangeUserPasswordAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, changeUserPasswordProtocol: ChangeUserPasswordProtocol): Try[LobbyMessageProtocol]
}

object ChangeUserPasswordAnalysisEngine {
  val name: String = "lobby.client2server.ChangeUserPassword"
}
