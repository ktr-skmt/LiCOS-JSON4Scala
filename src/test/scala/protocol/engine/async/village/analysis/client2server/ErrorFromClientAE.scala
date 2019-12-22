package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ErrorFromClientProtocol
import licos.protocol.engine.async.analysis.village.client2server.ErrorFromClientAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.ErrorFromClient
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class ErrorFromClientAE extends ErrorFromClientAnalysisEngine {
  override def process(box: VillageBOX, errorFromClient: ErrorFromClientProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(ErrorFromClient.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
