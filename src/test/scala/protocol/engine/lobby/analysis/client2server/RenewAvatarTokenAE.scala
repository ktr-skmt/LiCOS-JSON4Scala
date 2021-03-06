package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.RenewAvatarTokenProtocol
import licos.protocol.engine.analysis.lobby.client2server.RenewAvatarTokenAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.RenewAvatarToken

import scala.util.{Failure, Success, Try}

final class RenewAvatarTokenAE extends RenewAvatarTokenAnalysisEngine {
  override def process(box: LobbyBOX, renewAvatarTokenProtocol: RenewAvatarTokenProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(RenewAvatarToken.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
