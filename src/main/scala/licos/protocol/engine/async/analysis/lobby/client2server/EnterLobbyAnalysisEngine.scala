package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.EnterLobbyProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait EnterLobbyAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, enterLobbyProtocol: EnterLobbyProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object EnterLobbyAnalysisEngine {
  val name: String = "lobby.client2server.EnterLobby"
}
