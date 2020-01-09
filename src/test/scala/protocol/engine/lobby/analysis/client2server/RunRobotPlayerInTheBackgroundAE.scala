package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.RunRobotPlayerInTheBackgroundProtocol
import licos.protocol.engine.analysis.lobby.client2server.RunRobotPlayerInTheBackgroundAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.RunRobotPlayerInTheBackground

import scala.util.{Failure, Success, Try}

final class RunRobotPlayerInTheBackgroundAE extends RunRobotPlayerInTheBackgroundAnalysisEngine {
  override def process(
      box:                                   LobbyBOX,
      runRobotPlayerInTheBackgroundProtocol: RunRobotPlayerInTheBackgroundProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(RunRobotPlayerInTheBackground.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
