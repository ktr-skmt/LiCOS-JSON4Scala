package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.StopRobotPlayerProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait StopRobotPlayerAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, stopRobotPlayerProtocol: StopRobotPlayerProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object StopRobotPlayerAnalysisEngine {
  val name: String = "lobby.client2server.StopRobotPlayer"
}
