package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.SelectOnymousAudienceProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.SelectOnymousAudienceAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.SelectOnymousAudience

import scala.concurrent.{ExecutionContext, Future}

final class SelectOnymousAudienceAE extends SelectOnymousAudienceAnalysisEngine {
  override def process(box: LobbyBOX, selectOnymousAudienceProtocol: SelectOnymousAudienceProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(SelectOnymousAudience.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
