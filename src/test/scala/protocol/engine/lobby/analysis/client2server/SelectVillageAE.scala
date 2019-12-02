package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.SelectVillageProtocol
import licos.protocol.engine.analysis.lobby.client2server.SelectVillageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.SelectVillage
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class SelectVillageAE extends SelectVillageAnalysisEngine {
  override def process(box: LobbyBOX, selectVillageProtocol: SelectVillageProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(SelectVillage.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
