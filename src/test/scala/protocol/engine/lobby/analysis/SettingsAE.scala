package protocol.engine.lobby.analysis

import licos.json.element.lobby.server2client.JsonSettings
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.SettingsProtocol
import licos.protocol.engine.analysis.lobby.server2client.SettingsAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class SettingsAE extends SettingsAnalysisEngine {
  override def process(box: LobbyBOX, settingsProtocol: SettingsProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonSettings.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
