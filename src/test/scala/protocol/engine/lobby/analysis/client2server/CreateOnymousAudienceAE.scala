package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateOnymousAudienceProtocol
import licos.protocol.engine.analysis.lobby.client2server.CreateOnymousAudienceAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.CreateOnymousAudience

import scala.util.{Failure, Success, Try}

final class CreateOnymousAudienceAE extends CreateOnymousAudienceAnalysisEngine {
  override def process(
      box:                           LobbyBOX,
      createOnymousAudienceProtocol: CreateOnymousAudienceProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(CreateOnymousAudience.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
