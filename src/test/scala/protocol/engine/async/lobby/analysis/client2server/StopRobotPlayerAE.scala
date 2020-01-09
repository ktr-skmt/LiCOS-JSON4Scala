package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.StopRobotPlayerProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.StopRobotPlayerAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.StopRobotPlayer

import scala.concurrent.{ExecutionContext, Future}

final class StopRobotPlayerAE extends StopRobotPlayerAnalysisEngine {
  override def process(box: LobbyBOX, stopRobotPlayerProtocol: StopRobotPlayerProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(StopRobotPlayer.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
