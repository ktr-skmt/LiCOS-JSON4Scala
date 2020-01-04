package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.SelectOnymousAudienceProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait SelectOnymousAudienceAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, selectOnymousAudienceProtocol: SelectOnymousAudienceProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object SelectOnymousAudienceAnalysisEngine {
  val name: String = "lobby.client2server.SelectOnymousAudience"
}
