package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.RunRobotPlayerInTheBackgroundProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait RunRobotPlayerInTheBackgroundAnalysisEngine {
  def process(
      box:                                   LobbyBOX,
      runRobotPlayerInTheBackgroundProtocol: RunRobotPlayerInTheBackgroundProtocol
  ): Try[LobbyMessageProtocol]
}

object RunRobotPlayerInTheBackgroundAnalysisEngine {
  val name: String = "lobby.client2server.RunRobotPlayerInTheBackground"
}
