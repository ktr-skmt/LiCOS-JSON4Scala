package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.SelectVillageProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.SelectVillageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.SelectVillage
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class SelectVillageAE extends SelectVillageAnalysisEngine {
  override def process(box: LobbyBOX, selectVillageProtocol: SelectVillageProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(SelectVillage.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
