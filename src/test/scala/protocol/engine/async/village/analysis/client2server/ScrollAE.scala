package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ScrollProtocol
import licos.protocol.engine.async.analysis.village.client2server.ScrollAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.Scroll
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class ScrollAE extends ScrollAnalysisEngine {
  override def process(box: VillageBOX, scroll: ScrollProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(Scroll.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
