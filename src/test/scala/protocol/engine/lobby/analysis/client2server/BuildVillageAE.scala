package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.BuildVillageProtocol
import licos.protocol.engine.analysis.lobby.client2server.BuildVillageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.BuildVillage
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class BuildVillageAE extends BuildVillageAnalysisEngine {
  override def process(box: LobbyBOX, buildVillageProtocol: BuildVillageProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(BuildVillage.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
