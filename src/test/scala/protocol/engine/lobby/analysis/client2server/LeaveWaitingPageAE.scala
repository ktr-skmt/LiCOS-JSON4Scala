package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.LeaveWaitingPageProtocol
import licos.protocol.engine.analysis.lobby.client2server.LeaveWaitingPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.LeaveWaitingPage
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class LeaveWaitingPageAE extends LeaveWaitingPageAnalysisEngine {
  override def process(box: LobbyBOX, leaveWaitingPageProtocol: LeaveWaitingPageProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(LeaveWaitingPage.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
