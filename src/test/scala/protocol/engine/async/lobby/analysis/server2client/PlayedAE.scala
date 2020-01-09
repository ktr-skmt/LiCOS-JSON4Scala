package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.PlayedProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.PlayedAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2client.Played
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class PlayedAE extends PlayedAnalysisEngine {
  override def process(box: LobbyBOX, playedProtocol: PlayedProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(Played.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
