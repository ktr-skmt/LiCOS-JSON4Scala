package protocol.engine.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.SearchResultProtocol
import licos.protocol.engine.analysis.lobby.server2client.SearchResultAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2client.SearchResult
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

final class SearchResultAE extends SearchResultAnalysisEngine {
  override def process(box: LobbyBOX, searchResultProtocol: SearchResultProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(SearchResult.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
