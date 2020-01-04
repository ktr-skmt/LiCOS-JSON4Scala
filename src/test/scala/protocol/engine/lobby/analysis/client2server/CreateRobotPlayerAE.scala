package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateRobotPlayerProtocol
import licos.protocol.engine.analysis.lobby.client2server.CreateRobotPlayerAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.CreateRobotPlayer

import scala.util.{Failure, Success, Try}

final class CreateRobotPlayerAE extends CreateRobotPlayerAnalysisEngine {
  override def process(
      box:                       LobbyBOX,
      createRobotPlayerProtocol: CreateRobotPlayerProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(CreateRobotPlayer.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
