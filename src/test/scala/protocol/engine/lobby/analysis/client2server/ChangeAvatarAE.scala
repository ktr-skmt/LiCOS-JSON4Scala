package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeAvatarProtocol
import licos.protocol.engine.analysis.lobby.client2server.ChangeAvatarAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.ChangeAvatar

import scala.util.{Failure, Success, Try}

final class ChangeAvatarAE extends ChangeAvatarAnalysisEngine {
  override def process(box: LobbyBOX, changeAvatarProtocol: ChangeAvatarProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(ChangeAvatar.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
