package protocol.engine.lobby.analysis

import licos.json.element.lobby.client2server.JsonChangeUserPassword
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserPasswordProtocol
import licos.protocol.engine.analysis.lobby.client2server.ChangeUserPasswordAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class ChangeUserPasswordAE extends ChangeUserPasswordAnalysisEngine {
  override def process(box: LobbyBOX, changeUserPasswordProtocol: ChangeUserPasswordProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonChangeUserPassword.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
