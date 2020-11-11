package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.IdSearchProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.IdSearchAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.IdSearch
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class IdSearchAE extends IdSearchAnalysisEngine {
  override def process(box: LobbyBOX, idSearchProtocol: IdSearchProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(IdSearch.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
