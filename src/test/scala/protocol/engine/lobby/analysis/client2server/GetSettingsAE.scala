package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.GetSettingsProtocol
import licos.protocol.engine.analysis.lobby.client2server.GetSettingsAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.GetSettings
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class GetSettingsAE extends GetSettingsAnalysisEngine {
  override def process(box: LobbyBOX, getSettingsProtocol: GetSettingsProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(GetSettings.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
