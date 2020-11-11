package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.WaitingPageProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.WaitingPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2client.WaitingPage
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class WaitingPageAE extends WaitingPageAnalysisEngine {
  override def process(box: LobbyBOX, waitingPageProtocol: WaitingPageProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(WaitingPage.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
