package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.GameResultProtocol
import licos.protocol.engine.analysis.village.server2client.GameResultAnalysisEngine
import licos.protocol.engine.processing.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.GameResult
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

class GameResultAE extends GameResultAnalysisEngine {
  override def process(box: VillageBOX, gameResult: GameResultProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(GameResult.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
