package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.PlayProtocol
import licos.protocol.engine.analysis.lobby.client2server.PlayAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.Play
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

final class PlayAE extends PlayAnalysisEngine {
  override def process(box: LobbyBOX, playProtocol: PlayProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(Play.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
