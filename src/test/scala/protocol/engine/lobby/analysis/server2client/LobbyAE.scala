package protocol.engine.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.LobbyProtocol
import licos.protocol.engine.analysis.lobby.server2client.LobbyAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2client.Lobby
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class LobbyAE extends LobbyAnalysisEngine {
  override def process(box: LobbyBOX, lobbyProtocol: LobbyProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(Lobby.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
