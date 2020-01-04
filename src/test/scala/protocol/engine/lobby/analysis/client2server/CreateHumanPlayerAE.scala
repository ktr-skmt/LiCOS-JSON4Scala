package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateHumanPlayerProtocol
import licos.protocol.engine.analysis.lobby.client2server.CreateHumanPlayerAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.CreateHumanPlayer

import scala.util.{Failure, Success, Try}

final class CreateHumanPlayerAE extends CreateHumanPlayerAnalysisEngine {
  override def process(
      box:                       LobbyBOX,
      createHumanPlayerProtocol: CreateHumanPlayerProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(CreateHumanPlayer.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
