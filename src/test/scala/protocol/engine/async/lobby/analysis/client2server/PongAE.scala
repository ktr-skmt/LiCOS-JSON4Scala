package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.PongProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.PongAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.Pong
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class PongAE extends PongAnalysisEngine {
  override def process(box: LobbyBOX, pongProtocol: PongProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future(LobbyMessageTestProtocol(Pong.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
