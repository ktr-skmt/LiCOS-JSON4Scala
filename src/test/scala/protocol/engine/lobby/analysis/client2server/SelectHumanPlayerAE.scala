package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.SelectHumanPlayerProtocol
import licos.protocol.engine.analysis.lobby.client2server.SelectHumanPlayerAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.SelectHumanPlayer

import scala.util.{Failure, Success, Try}

final class SelectHumanPlayerAE extends SelectHumanPlayerAnalysisEngine {
  override def process(
      box:                       LobbyBOX,
      selectHumanPlayerProtocol: SelectHumanPlayerProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(SelectHumanPlayer.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
