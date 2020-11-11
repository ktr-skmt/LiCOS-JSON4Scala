package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.PlayProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.PlayAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.Play
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class PlayAE extends PlayAnalysisEngine {
  override def process(box: LobbyBOX, playProtocol: PlayProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(Play.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
