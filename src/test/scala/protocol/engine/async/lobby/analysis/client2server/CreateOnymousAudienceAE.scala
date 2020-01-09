package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateOnymousAudienceProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.CreateOnymousAudienceAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.CreateOnymousAudience

import scala.concurrent.{ExecutionContext, Future}

final class CreateOnymousAudienceAE extends CreateOnymousAudienceAnalysisEngine {
  override def process(box: LobbyBOX, createOnymousAudienceProtocol: CreateOnymousAudienceProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(CreateOnymousAudience.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }

  }
}
