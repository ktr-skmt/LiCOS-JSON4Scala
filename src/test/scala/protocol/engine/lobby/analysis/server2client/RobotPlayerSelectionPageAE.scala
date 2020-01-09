package protocol.engine.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.RobotPlayerSelectionPageProtocol
import licos.protocol.engine.analysis.lobby.server2client.RobotPlayerSelectionPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.server2client.RobotPlayerSelectionPage

import scala.util.{Failure, Success, Try}

final class RobotPlayerSelectionPageAE extends RobotPlayerSelectionPageAnalysisEngine {
  override def process(
      box:                              LobbyBOX,
      robotPlayerSelectionPageProtocol: RobotPlayerSelectionPageProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(RobotPlayerSelectionPage.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
