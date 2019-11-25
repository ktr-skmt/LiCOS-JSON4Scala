package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserNameProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait ChangeUserNameAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, changeUserNameProtocol: ChangeUserNameProtocol): Try[LobbyMessageProtocol]
}

object ChangeUserNameAnalysisEngine {
  val name: String = "lobby.client2server.ChangeUserName"
}
