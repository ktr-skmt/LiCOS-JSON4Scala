package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.SearchResultProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.SearchResultAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2client.SearchResult
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class SearchResultAE extends SearchResultAnalysisEngine {
  override def process(box: LobbyBOX, searchResultProtocol: SearchResultProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(SearchResult.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
