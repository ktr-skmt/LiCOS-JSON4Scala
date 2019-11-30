package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.SettingsProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait SettingsAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, settingsProtocol: SettingsProtocol): Try[LobbyMessageProtocol]
}

object SettingsAnalysisEngine {
  val name: String = "lobby.server2client.Settings"
}
