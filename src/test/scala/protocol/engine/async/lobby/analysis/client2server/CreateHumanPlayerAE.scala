package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateHumanPlayerProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.CreateHumanPlayerAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.CreateHumanPlayer

import scala.concurrent.{ExecutionContext, Future}

final class CreateHumanPlayerAE extends CreateHumanPlayerAnalysisEngine {
  override def process(box: LobbyBOX, createHumanPlayerProtocol: CreateHumanPlayerProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(CreateHumanPlayer.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
