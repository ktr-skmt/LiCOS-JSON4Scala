package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateRobotPlayerProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.CreateRobotPlayerAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.CreateRobotPlayer

import scala.concurrent.{ExecutionContext, Future}

final class CreateRobotPlayerAE extends CreateRobotPlayerAnalysisEngine {
  override def process(box: LobbyBOX, createRobotPlayerProtocol: CreateRobotPlayerProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(CreateRobotPlayer.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }

  }
}
