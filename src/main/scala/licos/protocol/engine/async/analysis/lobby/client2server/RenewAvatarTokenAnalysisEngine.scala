package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.RenewAvatarTokenProtocol
import licos.protocol.engine.async.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait RenewAvatarTokenAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, renewAvatarTokenProtocol: RenewAvatarTokenProtocol)(
      implicit ec: ExecutionContext): Future[LobbyMessageProtocol]
}

object RenewAvatarTokenAnalysisEngine {
  val name: String = "lobby.client2server.RenewAvatarToken"
}
