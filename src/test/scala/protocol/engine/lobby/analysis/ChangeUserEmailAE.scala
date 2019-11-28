package protocol.engine.lobby.analysis

import licos.json.element.lobby.client2server.JsonChangeUserEmail
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserEmailProtocol
import licos.protocol.engine.analysis.lobby.client2server.ChangeUserEmailAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class ChangeUserEmailAE extends ChangeUserEmailAnalysisEngine {
  override def process(box: LobbyBOX, changeUserEmailProtocol: ChangeUserEmailProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonChangeUserEmail.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
