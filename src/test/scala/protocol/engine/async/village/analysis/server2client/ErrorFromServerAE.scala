package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.ErrorFromServerProtocol
import licos.protocol.engine.async.analysis.village.server2client.ErrorFromServerAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.ErrorFromServer
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class ErrorFromServerAE extends ErrorFromServerAnalysisEngine {
  override def process(box: VillageBOX, errorFromServer: ErrorFromServerProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(ErrorFromServer.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
