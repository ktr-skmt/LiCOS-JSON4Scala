package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeLanguageProtocol
import licos.protocol.engine.analysis.lobby.client2server.ChangeLanguageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.ChangeLanguage
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class ChangeLanguageAE extends ChangeLanguageAnalysisEngine {
  override def process(box: LobbyBOX, changeLanguageProtocol: ChangeLanguageProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(ChangeLanguage.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
