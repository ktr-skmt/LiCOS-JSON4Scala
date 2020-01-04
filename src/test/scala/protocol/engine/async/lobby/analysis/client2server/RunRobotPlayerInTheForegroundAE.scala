package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.RunRobotPlayerInTheForegroundProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.RunRobotPlayerInTheForegroundAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.RunRobotPlayerInTheForeground

import scala.concurrent.{ExecutionContext, Future}

final class RunRobotPlayerInTheForegroundAE extends RunRobotPlayerInTheForegroundAnalysisEngine {
  override def process(box: LobbyBOX, runRobotPlayerInTheForegroundProtocol: RunRobotPlayerInTheForegroundProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(RunRobotPlayerInTheForeground.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
