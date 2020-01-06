package protocol.engine.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.HumanPlayerSelectionPageProtocol
import licos.protocol.engine.analysis.lobby.server2client.HumanPlayerSelectionPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.server2client.HumanPlayerSelectionPage

import scala.util.{Failure, Success, Try}

final class HumanPlayerSelectionPageAE extends HumanPlayerSelectionPageAnalysisEngine {
  override def process(
      box:                              LobbyBOX,
      humanPlayerSelectionPageProtocol: HumanPlayerSelectionPageProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(HumanPlayerSelectionPage.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
