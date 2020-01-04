package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.DeleteAvatarProtocol
import licos.protocol.engine.analysis.lobby.client2server.DeleteAvatarAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.DeleteAvatar

import scala.util.{Failure, Success, Try}

final class DeleteAvatarAE extends DeleteAvatarAnalysisEngine {
  override def process(box: LobbyBOX, deleteAvatarProtocol: DeleteAvatarProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(DeleteAvatar.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
