package protocol.engine.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.WaitingPageProtocol
import licos.protocol.engine.analysis.lobby.server2client.WaitingPageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2client.WaitingPage
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class WaitingPageAE extends WaitingPageAnalysisEngine {
  override def process(box: LobbyBOX, waitingPageProtocol: WaitingPageProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(WaitingPage.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
