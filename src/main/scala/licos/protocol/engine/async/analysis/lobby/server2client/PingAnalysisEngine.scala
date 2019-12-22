package licos.protocol.engine.async.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.PingProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait PingAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, pingProtocol: PingProtocol)(implicit ec: ExecutionContext): Future[LobbyMessageProtocol]
}

object PingAnalysisEngine {
  val name: String = "lobby.server2client.Ping"
}
