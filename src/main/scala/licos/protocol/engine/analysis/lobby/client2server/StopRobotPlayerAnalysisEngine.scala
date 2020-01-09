package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.StopRobotPlayerProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait StopRobotPlayerAnalysisEngine {
  def process(box: LobbyBOX, stopRobotPlayerProtocol: StopRobotPlayerProtocol): Try[LobbyMessageProtocol]
}

object StopRobotPlayerAnalysisEngine {
  val name: String = "lobby.client2server.StopRobotPlayer"
}
