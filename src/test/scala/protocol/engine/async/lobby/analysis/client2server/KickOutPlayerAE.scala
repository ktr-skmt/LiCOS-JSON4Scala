package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.KickOutPlayerProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.KickOutPlayerAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.KickOutPlayer
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class KickOutPlayerAE extends KickOutPlayerAnalysisEngine {
  override def process(box: LobbyBOX, kickOutPlayerProtocol: KickOutPlayerProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future(LobbyMessageTestProtocol(KickOutPlayer.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
