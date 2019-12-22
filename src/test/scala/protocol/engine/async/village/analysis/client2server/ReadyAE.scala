package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReadyProtocol
import licos.protocol.engine.async.analysis.village.client2server.ReadyAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.Ready
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class ReadyAE extends ReadyAnalysisEngine {
  override def process(box: VillageBOX, readyProtocol: ReadyProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(Ready.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
