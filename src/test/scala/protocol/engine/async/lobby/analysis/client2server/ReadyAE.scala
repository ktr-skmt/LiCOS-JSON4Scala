package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ReadyProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.ReadyAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.Ready
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class ReadyAE extends ReadyAnalysisEngine {
  override def process(box: LobbyBOX, readyProtocol: ReadyProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future(LobbyMessageTestProtocol(Ready.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
