package licos.protocol.engine.async.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.NewAvatarTokenProtocol
import licos.protocol.engine.async.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait NewAvatarTokenAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, newAvatarTokenProtocol: NewAvatarTokenProtocol)(
      implicit ec: ExecutionContext): Future[LobbyMessageProtocol]
}

object NewAvatarTokenAnalysisEngine {
  val name: String = "lobby.server2client.NewAvatarToken"
}
