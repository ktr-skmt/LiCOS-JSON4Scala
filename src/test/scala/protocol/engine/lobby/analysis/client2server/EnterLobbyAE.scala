package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.EnterLobbyProtocol
import licos.protocol.engine.analysis.lobby.client2server.EnterLobbyAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.EnterLobby
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class EnterLobbyAE extends EnterLobbyAnalysisEngine {
  override def process(box: LobbyBOX, enterLobbyProtocol: EnterLobbyProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(EnterLobby.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
