package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.SettingsProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.SettingsAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2client.Settings
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class SettingsAE extends SettingsAnalysisEngine {
  override def process(box: LobbyBOX, settingsProtocol: SettingsProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(Settings.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
