package protocol.engine.lobby.analysis

import licos.json.element.lobby.client2server.JsonKickOutPlayer
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.KickOutPlayerProtocol
import licos.protocol.engine.analysis.lobby.client2server.KickOutPlayerAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class KickOutPlayerAE extends KickOutPlayerAnalysisEngine {
  override def process(box: LobbyBOX, kickOutPlayerProtocol: KickOutPlayerProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonKickOutPlayer.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
