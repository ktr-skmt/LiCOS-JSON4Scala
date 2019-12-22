package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.KickOutPlayerProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait KickOutPlayerAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, kickOutPlayerProtocol: KickOutPlayerProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object KickOutPlayerAnalysisEngine {
  val name: String = "lobby.client2server.KickOutPlayer"
}
