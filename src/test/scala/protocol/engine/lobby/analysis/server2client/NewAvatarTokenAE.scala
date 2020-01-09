package protocol.engine.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.NewAvatarTokenProtocol
import licos.protocol.engine.analysis.lobby.server2client.NewAvatarTokenAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.server2client.NewAvatarToken

import scala.util.{Failure, Success, Try}

final class NewAvatarTokenAE extends NewAvatarTokenAnalysisEngine {
  override def process(box: LobbyBOX, newAvatarTokenProtocol: NewAvatarTokenProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(NewAvatarToken.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
