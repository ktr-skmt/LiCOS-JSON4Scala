package protocol.engine.lobby.analysis

import licos.json.element.lobby.client2server.JsonChangeLang
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeLangProtocol
import licos.protocol.engine.analysis.lobby.client2server.ChangeLangAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class ChangeLangAE extends ChangeLangAnalysisEngine {
  override def process(box: LobbyBOX, changeLangProtocol: ChangeLangProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonChangeLang.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
