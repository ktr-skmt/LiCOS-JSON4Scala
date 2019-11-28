package protocol.engine.lobby.analysis

import licos.json.element.lobby.client2server.JsonLeaveWaitingPage
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.LeaveWaitingPageProtocol
import licos.protocol.engine.analysis.lobby.client2server.LeaveWaitingPageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class LeaveWaitingPageAE extends LeaveWaitingPageAnalysisEngine {
  override def process(box: LobbyBOX, leaveWaitingPageProtocol: LeaveWaitingPageProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonLeaveWaitingPage.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
