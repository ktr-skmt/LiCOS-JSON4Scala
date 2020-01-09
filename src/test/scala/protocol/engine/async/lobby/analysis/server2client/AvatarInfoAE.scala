package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AvatarInfoProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.AvatarInfoAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.server2client.AvatarInfo
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class AvatarInfoAE extends AvatarInfoAnalysisEngine {
  override def process(box: LobbyBOX, avatarInfoProtocol: AvatarInfoProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(AvatarInfo.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
