package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.DeleteAvatarProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait DeleteAvatarAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, deleteAvatarProtocol: DeleteAvatarProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object DeleteAvatarAnalysisEngine {
  val name: String = "lobby.client2server.DeleteAvatar"
}
