package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.PongProtocol
import licos.protocol.engine.analysis.lobby.client2server.PongAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.Pong
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class PongAE extends PongAnalysisEngine {
  override def process(box: LobbyBOX, pongProtocol: PongProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(Pong.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
