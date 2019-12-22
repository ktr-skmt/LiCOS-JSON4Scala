package licos.protocol.engine.async.analysis.lobby.server2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2server.PlayedWithTokenProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait PlayedWithTokenAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, playedWithTokenProtocol: PlayedWithTokenProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object PlayedWithTokenAnalysisEngine {
  val name: String = "lobby.server2server.PlayedWithToken"
}
