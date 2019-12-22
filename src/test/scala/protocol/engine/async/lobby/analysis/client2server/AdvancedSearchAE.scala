package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.AdvancedSearchProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.AdvancedSearchAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.AdvancedSearch
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class AdvancedSearchAE extends AdvancedSearchAnalysisEngine {
  override def process(box: LobbyBOX, advancedSearchProtocol: AdvancedSearchProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future(LobbyMessageTestProtocol(AdvancedSearch.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
