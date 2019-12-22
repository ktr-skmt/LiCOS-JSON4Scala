package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeLangProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.ChangeLangAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.ChangeLang
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class ChangeLangAE extends ChangeLangAnalysisEngine {
  override def process(box: LobbyBOX, changeLangProtocol: ChangeLangProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future(LobbyMessageTestProtocol(ChangeLang.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
