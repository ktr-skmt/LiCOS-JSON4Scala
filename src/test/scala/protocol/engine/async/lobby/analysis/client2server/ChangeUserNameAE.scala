package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserNameProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.ChangeUserNameAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.ChangeUserName
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class ChangeUserNameAE extends ChangeUserNameAnalysisEngine {
  override def process(box: LobbyBOX, changeUserNameProtocol: ChangeUserNameProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(ChangeUserName.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
