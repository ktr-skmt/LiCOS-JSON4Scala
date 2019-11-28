package protocol.engine.lobby.analysis

import licos.json.element.lobby.client2server.JsonSelectVillage
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.SelectVillageProtocol
import licos.protocol.engine.analysis.lobby.client2server.SelectVillageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class SelectVillageAE extends SelectVillageAnalysisEngine {
  override def process(box: LobbyBOX, selectVillageProtocol: SelectVillageProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonSelectVillage.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
