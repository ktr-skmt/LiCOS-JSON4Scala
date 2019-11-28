package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.IdSearchProtocol
import licos.protocol.engine.analysis.lobby.client2server.IdSearchAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.IdSearch
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class IdSearchAE extends IdSearchAnalysisEngine {
  override def process(box: LobbyBOX, idSearchProtocol: IdSearchProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(IdSearch.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
