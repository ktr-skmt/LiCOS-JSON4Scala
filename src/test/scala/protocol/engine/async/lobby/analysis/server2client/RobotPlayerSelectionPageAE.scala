package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.RobotPlayerSelectionPageProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.RobotPlayerSelectionPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.server2client.RobotPlayerSelectionPage

import scala.concurrent.{ExecutionContext, Future}

final class RobotPlayerSelectionPageAE extends RobotPlayerSelectionPageAnalysisEngine {
  override def process(box: LobbyBOX, robotPlayerSelectionPageProtocol: RobotPlayerSelectionPageProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(RobotPlayerSelectionPage.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
