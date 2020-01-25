package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeAvatarProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait ChangeAvatarAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, changeAvatarProtocol: ChangeAvatarProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object ChangeAvatarAnalysisEngine {
  val name: String = "lobby.client2server.ChangeAvatar"
}
