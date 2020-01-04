package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.RunRobotPlayerInTheForegroundProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait RunRobotPlayerInTheForegroundAnalysisEngine {
  def process(
      box:                                   LobbyBOX,
      runRobotPlayerInTheForegroundProtocol: RunRobotPlayerInTheForegroundProtocol
  ): Try[LobbyMessageProtocol]
}

object RunRobotPlayerInTheForegroundAnalysisEngine {
  val name: String = "lobby.client2server.RunRobotPlayerInTheForeground"
}
