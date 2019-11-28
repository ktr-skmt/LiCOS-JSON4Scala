package protocol.engine.lobby.analysis

import licos.json.element.lobby.client2server.JsonAdvancedSearch
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.AdvancedSearchProtocol
import licos.protocol.engine.analysis.lobby.client2server.AdvancedSearchAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class AdvancedSearchAE extends AdvancedSearchAnalysisEngine {
  override def process(box: LobbyBOX, advancedSearchProtocol: AdvancedSearchProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonAdvancedSearch.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
