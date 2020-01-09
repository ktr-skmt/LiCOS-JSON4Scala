package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserEmailProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.ChangeUserEmailAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.ChangeUserEmail
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class ChangeUserEmailAE extends ChangeUserEmailAnalysisEngine {
  override def process(box: LobbyBOX, changeUserEmailProtocol: ChangeUserEmailProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(ChangeUserEmail.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
