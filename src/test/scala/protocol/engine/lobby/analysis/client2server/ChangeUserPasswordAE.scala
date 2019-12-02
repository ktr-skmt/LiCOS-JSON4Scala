package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserPasswordProtocol
import licos.protocol.engine.analysis.lobby.client2server.ChangeUserPasswordAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.ChangeUserPassword
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

final class ChangeUserPasswordAE extends ChangeUserPasswordAnalysisEngine {
  override def process(
      box:                        LobbyBOX,
      changeUserPasswordProtocol: ChangeUserPasswordProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(ChangeUserPassword.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
