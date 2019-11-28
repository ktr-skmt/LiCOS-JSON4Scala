package protocol.engine.lobby.analysis

import licos.json.element.lobby.client2server.JsonPlay
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.PlayProtocol
import licos.protocol.engine.analysis.lobby.client2server.PlayAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class PlayAE extends PlayAnalysisEngine {
  override def process(box: LobbyBOX, playProtocol: PlayProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonPlay.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
