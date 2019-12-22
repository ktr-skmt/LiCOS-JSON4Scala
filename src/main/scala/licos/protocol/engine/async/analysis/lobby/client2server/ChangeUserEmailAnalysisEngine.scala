package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserEmailProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait ChangeUserEmailAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, changeUserEmailProtocol: ChangeUserEmailProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object ChangeUserEmailAnalysisEngine {
  val name: String = "lobby.client2server.ChangeUserEmail"
}
