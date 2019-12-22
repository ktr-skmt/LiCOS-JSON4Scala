package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ReadyProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait ReadyAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, readyProtocol: ReadyProtocol)(implicit ec: ExecutionContext): Future[LobbyMessageProtocol]
}

object ReadyAnalysisEngine {
  val name: String = "lobby.client2server.Ready"
}
