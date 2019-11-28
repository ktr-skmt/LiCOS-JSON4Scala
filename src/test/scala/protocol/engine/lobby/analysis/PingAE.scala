package protocol.engine.lobby.analysis

import licos.json.element.lobby.server2client.JsonPing
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.PingProtocol
import licos.protocol.engine.analysis.lobby.server2client.PingAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class PingAE extends PingAnalysisEngine {
  override def process(box: LobbyBOX, pingProtocol: PingProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonPing.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
