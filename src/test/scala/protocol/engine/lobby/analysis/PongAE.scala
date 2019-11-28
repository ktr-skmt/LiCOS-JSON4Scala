package protocol.engine.lobby.analysis

import licos.json.element.lobby.client2server.JsonPong
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.PongProtocol
import licos.protocol.engine.analysis.lobby.client2server.PongAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class PongAE extends PongAnalysisEngine {
  override def process(box: LobbyBOX, pongProtocol: PongProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonPong.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
