package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.RenewAvatarTokenProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait RenewAvatarTokenAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, renewAvatarTokenProtocol: RenewAvatarTokenProtocol): Try[LobbyMessageProtocol]
}

object RenewAvatarTokenAnalysisEngine {
  val name: String = "lobby.client2server.RenewAvatarToken"
}
