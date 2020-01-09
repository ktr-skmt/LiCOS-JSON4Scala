package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.UpdateAvatarProtocol
import licos.protocol.engine.analysis.lobby.client2server.UpdateAvatarAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.UpdateAvatar

import scala.util.{Failure, Success, Try}

final class UpdateAvatarAE extends UpdateAvatarAnalysisEngine {
  override def process(box: LobbyBOX, updateAvatarProtocol: UpdateAvatarProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(UpdateAvatar.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
