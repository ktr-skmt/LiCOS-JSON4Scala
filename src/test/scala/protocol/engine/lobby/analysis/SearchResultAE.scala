package protocol.engine.lobby.analysis

import licos.json.element.lobby.server2client.JsonSearchResult
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.SearchResultProtocol
import licos.protocol.engine.analysis.lobby.server2client.SearchResultAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class SearchResultAE extends SearchResultAnalysisEngine {
  override def process(box: LobbyBOX, searchResultProtocol: SearchResultProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonSearchResult.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
