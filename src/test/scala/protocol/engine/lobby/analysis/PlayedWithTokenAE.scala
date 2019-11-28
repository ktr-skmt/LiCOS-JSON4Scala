package protocol.engine.lobby.analysis

import licos.json.element.lobby.server2server.JsonPlayedWithToken
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2server.PlayedWithTokenProtocol
import licos.protocol.engine.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class PlayedWithTokenAE extends PlayedWithTokenAnalysisEngine {
  override def process(box: LobbyBOX, playedWithTokenProtocol: PlayedWithTokenProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonPlayedWithToken.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
