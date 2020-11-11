package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.LobbyProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.LobbyAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2client.Lobby
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class LobbyAE extends LobbyAnalysisEngine {
  override def process(box: LobbyBOX, lobbyProtocol: LobbyProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(Lobby.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
