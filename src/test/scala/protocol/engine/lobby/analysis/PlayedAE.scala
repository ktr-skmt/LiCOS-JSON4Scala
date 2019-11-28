package protocol.engine.lobby.analysis

import licos.json.element.lobby.server2client.JsonPlayed
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.PlayedProtocol
import licos.protocol.engine.analysis.lobby.server2client.PlayedAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class PlayedAE extends PlayedAnalysisEngine {
  override def process(box: LobbyBOX, playedProtocol: PlayedProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonPlayed.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
