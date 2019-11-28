package protocol.engine.lobby.analysis

import licos.json.element.lobby.server2client.JsonAvatarInfo
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AvatarInfoProtocol
import licos.protocol.engine.analysis.lobby.server2client.AvatarInfoAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class AvatarInfoAE extends AvatarInfoAnalysisEngine {
  override def process(box: LobbyBOX, avatarInfoProtocol: AvatarInfoProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonAvatarInfo.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
