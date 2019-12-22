package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.StarProtocol
import licos.protocol.engine.async.analysis.village.client2server.StarAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.Star
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class StarAE extends StarAnalysisEngine {
  override def process(box: VillageBOX, star: StarProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future(VillageMessageTestProtocol(Star.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
