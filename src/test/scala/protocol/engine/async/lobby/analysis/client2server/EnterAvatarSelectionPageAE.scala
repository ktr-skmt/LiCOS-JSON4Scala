package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.EnterAvatarSelectionPageProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.EnterAvatarSelectionPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.EnterAvatarSelectionPage

import scala.concurrent.{ExecutionContext, Future}

final class EnterAvatarSelectionPageAE extends EnterAvatarSelectionPageAnalysisEngine {
  override def process(box: LobbyBOX, enterAvatarSelectionPageProtocol: EnterAvatarSelectionPageProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(EnterAvatarSelectionPage.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
