package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeLangProtocol
import licos.protocol.engine.analysis.lobby.client2server.ChangeLangAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.ChangeLang
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class ChangeLangAE extends ChangeLangAnalysisEngine {
  override def process(box: LobbyBOX, changeLangProtocol: ChangeLangProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(ChangeLang.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
