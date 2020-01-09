package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateRobotPlayerProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait CreateRobotPlayerAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, createRobotPlayerProtocol: CreateRobotPlayerProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object CreateRobotPlayerAnalysisEngine {
  val name: String = "lobby.client2server.CreateRobotPlayer"
}
