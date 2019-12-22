package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.PingProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.PingAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2client.Ping
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class PingAE extends PingAnalysisEngine {
  override def process(box: LobbyBOX, pingProtocol: PingProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future(LobbyMessageTestProtocol(Ping.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
