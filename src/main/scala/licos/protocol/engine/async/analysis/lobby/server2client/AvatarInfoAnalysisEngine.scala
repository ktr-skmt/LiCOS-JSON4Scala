package licos.protocol.engine.async.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AvatarInfoProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait AvatarInfoAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, avatarInfoProtocol: AvatarInfoProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object AvatarInfoAnalysisEngine {
  val name: String = "lobby.server2client.AvatarInfo"
}
