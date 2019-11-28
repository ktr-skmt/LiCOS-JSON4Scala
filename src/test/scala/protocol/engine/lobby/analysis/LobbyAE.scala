package protocol.engine.lobby.analysis

import licos.json.element.lobby.server2client.JsonLobby
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.LobbyProtocol
import licos.protocol.engine.analysis.lobby.server2client.LobbyAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class LobbyAE extends LobbyAnalysisEngine {
  override def process(box: LobbyBOX, lobbyProtocol: LobbyProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonLobby.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
