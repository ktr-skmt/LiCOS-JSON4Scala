package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeLanguageProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.ChangeLanguageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.ChangeLanguage
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class ChangeLanguageAE extends ChangeLanguageAnalysisEngine {
  override def process(box: LobbyBOX, changeLanguageProtocol: ChangeLanguageProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(ChangeLanguage.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
