package protocol.engine.lobby.analysis

import licos.json.element.lobby.server2client.JsonWaitingPage
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.WaitingPageProtocol
import licos.protocol.engine.analysis.lobby.server2client.WaitingPageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class WaitingPageAE extends WaitingPageAnalysisEngine {
  override def process(box: LobbyBOX, waitingPageProtocol: WaitingPageProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonWaitingPage.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
