package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.GetAvatarInfoProtocol
import licos.protocol.engine.analysis.lobby.client2server.GetAvatarInfoAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.GetAvatarInfo
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class GetAvatarInfoAE extends GetAvatarInfoAnalysisEngine {
  override def process(box: LobbyBOX, getAvatarInfoProtocol: GetAvatarInfoProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(GetAvatarInfo.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
