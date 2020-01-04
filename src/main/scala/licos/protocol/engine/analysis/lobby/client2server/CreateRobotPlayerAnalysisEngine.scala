package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateRobotPlayerProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait CreateRobotPlayerAnalysisEngine {
  def process(box: LobbyBOX, createRobotPlayerProtocol: CreateRobotPlayerProtocol): Try[LobbyMessageProtocol]
}

object CreateRobotPlayerAnalysisEngine {
  val name: String = "lobby.client2server.CreateRobotPlayer"
}
