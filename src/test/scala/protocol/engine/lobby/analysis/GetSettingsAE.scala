package protocol.engine.lobby.analysis

import licos.json.element.lobby.client2server.JsonGetSettings
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.GetSettingsProtocol
import licos.protocol.engine.analysis.lobby.client2server.GetSettingsAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class GetSettingsAE extends GetSettingsAnalysisEngine {
  override def process(box: LobbyBOX, getSettingsProtocol: GetSettingsProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonGetSettings.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
