package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserEmailProtocol
import licos.protocol.engine.analysis.lobby.client2server.ChangeUserEmailAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.ChangeUserEmail
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class ChangeUserEmailAE extends ChangeUserEmailAnalysisEngine {
  override def process(box: LobbyBOX, changeUserEmailProtocol: ChangeUserEmailProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(ChangeUserEmail.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
