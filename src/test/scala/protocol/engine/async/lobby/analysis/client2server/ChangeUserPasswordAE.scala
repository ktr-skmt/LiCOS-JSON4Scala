package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserPasswordProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.ChangeUserPasswordAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.ChangeUserPassword
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class ChangeUserPasswordAE extends ChangeUserPasswordAnalysisEngine {
  override def process(
      box:                        LobbyBOX,
      changeUserPasswordProtocol: ChangeUserPasswordProtocol
  )(implicit ec:                  ExecutionContext): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(ChangeUserPassword.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
