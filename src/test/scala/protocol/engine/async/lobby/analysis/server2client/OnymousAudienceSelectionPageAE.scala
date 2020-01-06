package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.OnymousAudienceSelectionPageProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.OnymousAudienceSelectionPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.server2client.OnymousAudienceSelectionPage

import scala.concurrent.{ExecutionContext, Future}

final class OnymousAudienceSelectionPageAE extends OnymousAudienceSelectionPageAnalysisEngine {
  override def process(box: LobbyBOX, onymousAudienceSelectionPageProtocol: OnymousAudienceSelectionPageProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(OnymousAudienceSelectionPage.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
