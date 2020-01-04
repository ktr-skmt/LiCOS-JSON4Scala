package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.RunRobotPlayerInTheBackgroundProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait RunRobotPlayerInTheBackgroundAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, runRobotPlayerInTheBackgroundProtocol: RunRobotPlayerInTheBackgroundProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object RunRobotPlayerInTheBackgroundAnalysisEngine {
  val name: String = "lobby.client2server.RunRobotPlayerInTheBackground"
}
