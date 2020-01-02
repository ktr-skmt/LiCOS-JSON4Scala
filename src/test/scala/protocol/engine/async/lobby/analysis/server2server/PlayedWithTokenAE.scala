package protocol.engine.async.lobby.analysis.server2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2server.PlayedWithTokenProtocol
import licos.protocol.engine.async.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2server.PlayedWithToken
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class PlayedWithTokenAE extends PlayedWithTokenAnalysisEngine {
  override def process(box: LobbyBOX, playedWithTokenProtocol: PlayedWithTokenProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(PlayedWithToken.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
