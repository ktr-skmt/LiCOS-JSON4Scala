package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.BuildVillageProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.BuildVillageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.BuildVillage
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class BuildVillageAE extends BuildVillageAnalysisEngine {
  override def process(box: LobbyBOX, buildVillageProtocol: BuildVillageProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future(LobbyMessageTestProtocol(BuildVillage.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
