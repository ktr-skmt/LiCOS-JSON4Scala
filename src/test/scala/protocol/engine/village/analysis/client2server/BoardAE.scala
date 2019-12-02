package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.BoardProtocol
import licos.protocol.engine.analysis.village.client2server.BoardAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.Board
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

final class BoardAE extends BoardAnalysisEngine {
  override def process(box: VillageBOX, board: BoardProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(Board.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
