package protocol.engine.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AvatarInfoProtocol
import licos.protocol.engine.analysis.lobby.server2client.AvatarInfoAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2client.AvatarInfo
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class AvatarInfoAE extends AvatarInfoAnalysisEngine {
  override def process(box: LobbyBOX, avatarInfoProtocol: AvatarInfoProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(AvatarInfo.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
