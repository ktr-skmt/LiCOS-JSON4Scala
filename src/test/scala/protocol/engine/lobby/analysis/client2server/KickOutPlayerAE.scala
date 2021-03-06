package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.KickOutPlayerProtocol
import licos.protocol.engine.analysis.lobby.client2server.KickOutPlayerAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.KickOutPlayer
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class KickOutPlayerAE extends KickOutPlayerAnalysisEngine {
  override def process(box: LobbyBOX, kickOutPlayerProtocol: KickOutPlayerProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(KickOutPlayer.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
