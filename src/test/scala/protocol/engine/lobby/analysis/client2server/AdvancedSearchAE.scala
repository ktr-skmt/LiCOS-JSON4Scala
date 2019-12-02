package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.AdvancedSearchProtocol
import licos.protocol.engine.analysis.lobby.client2server.AdvancedSearchAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.AdvancedSearch
import protocol.engine.lobby.LobbyBox

import scala.util.{Failure, Success, Try}

final class AdvancedSearchAE extends AdvancedSearchAnalysisEngine {
  override def process(box: LobbyBOX, advancedSearchProtocol: AdvancedSearchProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(AdvancedSearch.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
