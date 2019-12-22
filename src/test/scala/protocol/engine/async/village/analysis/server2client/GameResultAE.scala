package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.GameResultProtocol
import licos.protocol.engine.async.analysis.village.server2client.GameResultAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.GameResult
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class GameResultAE extends GameResultAnalysisEngine {
  override def process(box: VillageBOX, gameResult: GameResultProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(GameResult.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
