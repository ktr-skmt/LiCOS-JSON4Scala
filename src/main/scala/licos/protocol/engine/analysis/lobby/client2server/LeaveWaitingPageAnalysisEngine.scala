package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.LeaveWaitingPageProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait LeaveWaitingPageAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, leaveWaitingPageProtocol: LeaveWaitingPageProtocol): Try[LobbyMessageProtocol]
}

object LeaveWaitingPageAnalysisEngine {
  val name: String = "lobby.client2server.LeaveWaitingPage"
}
