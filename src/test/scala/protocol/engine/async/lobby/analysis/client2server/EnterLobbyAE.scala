package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.EnterLobbyProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.EnterLobbyAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.EnterLobby
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class EnterLobbyAE extends EnterLobbyAnalysisEngine {
  override def process(box: LobbyBOX, enterLobbyProtocol: EnterLobbyProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(EnterLobby.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
