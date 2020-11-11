package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.HumanPlayerSelectionPageProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.HumanPlayerSelectionPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.server2client.HumanPlayerSelectionPage

import scala.concurrent.{ExecutionContext, Future}

final class HumanPlayerSelectionPageAE extends HumanPlayerSelectionPageAnalysisEngine {
  override def process(box: LobbyBOX, humanPlayerSelectionPageProtocol: HumanPlayerSelectionPageProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(HumanPlayerSelectionPage.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
