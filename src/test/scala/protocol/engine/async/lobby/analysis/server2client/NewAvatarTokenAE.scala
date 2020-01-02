package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.NewAvatarTokenProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.NewAvatarTokenAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.server2client.NewAvatarToken

import scala.concurrent.{ExecutionContext, Future}

final class NewAvatarTokenAE extends NewAvatarTokenAnalysisEngine {
  override def process(box: LobbyBOX, newAvatarTokenProtocol: NewAvatarTokenProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(NewAvatarToken.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
