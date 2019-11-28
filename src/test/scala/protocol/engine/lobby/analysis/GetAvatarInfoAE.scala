package protocol.engine.lobby.analysis

import licos.json.element.lobby.server2client.JsonGetAvatarInfo
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.GetAvatarInfoProtocol
import licos.protocol.engine.analysis.lobby.client2server.GetAvatarInfoAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class GetAvatarInfoAE extends GetAvatarInfoAnalysisEngine {
  override def process(box: LobbyBOX, getAvatarInfoProtocol: GetAvatarInfoProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonGetAvatarInfo.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
