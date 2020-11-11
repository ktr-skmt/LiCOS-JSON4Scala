package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.EnterAvatarSelectionPageProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait EnterAvatarSelectionPageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, enterAvatarSelectionPageProtocol: EnterAvatarSelectionPageProtocol)(implicit
      ec:          ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object EnterAvatarSelectionPageAnalysisEngine {
  val name: String = "lobby.client2server.EnterAvatarSelectionPage"
}
