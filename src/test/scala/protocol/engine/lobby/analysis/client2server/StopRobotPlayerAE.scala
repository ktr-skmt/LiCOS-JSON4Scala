package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.StopRobotPlayerProtocol
import licos.protocol.engine.analysis.lobby.client2server.StopRobotPlayerAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.StopRobotPlayer

import scala.util.{Failure, Success, Try}

final class StopRobotPlayerAE extends StopRobotPlayerAnalysisEngine {
  override def process(box: LobbyBOX, stopRobotPlayerProtocol: StopRobotPlayerProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(StopRobotPlayer.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
