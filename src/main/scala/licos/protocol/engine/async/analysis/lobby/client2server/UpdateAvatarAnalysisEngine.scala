package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.UpdateAvatarProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait UpdateAvatarAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, updateAvatarProtocol: UpdateAvatarProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object UpdateAvatarAnalysisEngine {
  val name: String = "lobby.client2server.UpdateAvatar"
}
