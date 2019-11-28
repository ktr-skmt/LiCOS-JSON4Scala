package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserNameProtocol
import licos.protocol.engine.analysis.lobby.client2server.ChangeUserNameAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.ChangeUserName
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class ChangeUserNameAE extends ChangeUserNameAnalysisEngine {
  override def process(box: LobbyBOX, changeUserNameProtocol: ChangeUserNameProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(ChangeUserName.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
