package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.FlavorTextProtocol
import licos.protocol.engine.async.analysis.village.server2client.FlavorTextAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.FlavorText
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class FlavorTextAE extends FlavorTextAnalysisEngine {
  override def process(box: VillageBOX, flavorText: FlavorTextProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(FlavorText.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
