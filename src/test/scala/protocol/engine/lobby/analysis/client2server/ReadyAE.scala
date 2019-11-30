package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ReadyProtocol
import licos.protocol.engine.analysis.lobby.client2server.ReadyAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.Ready
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class ReadyAE extends ReadyAnalysisEngine {
  override def process(box: LobbyBOX, readyProtocol: ReadyProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(Ready.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
