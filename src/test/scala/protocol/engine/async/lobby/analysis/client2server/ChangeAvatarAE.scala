package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeAvatarProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.ChangeAvatarAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.ChangeAvatar

import scala.concurrent.{ExecutionContext, Future}

final class ChangeAvatarAE extends ChangeAvatarAnalysisEngine {
  override def process(box: LobbyBOX, changeAvatarProtocol: ChangeAvatarProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(ChangeAvatar.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
