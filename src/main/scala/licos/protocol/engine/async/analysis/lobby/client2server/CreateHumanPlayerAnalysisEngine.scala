package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateHumanPlayerProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait CreateHumanPlayerAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, createHumanPlayerProtocol: CreateHumanPlayerProtocol)(implicit
      ec:          ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object CreateHumanPlayerAnalysisEngine {
  val name: String = "lobby.client2server.CreateHumanPlayer"
}
