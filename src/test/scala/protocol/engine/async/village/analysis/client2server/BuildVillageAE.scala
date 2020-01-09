package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.BuildVillageProtocol
import licos.protocol.engine.async.analysis.village.client2server.BuildVillageAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.BuildVillage
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class BuildVillageAE extends BuildVillageAnalysisEngine {
  override def process(box: VillageBOX, buildVillageProtocol: BuildVillageProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Future.successful(VillageMessageTestProtocol(BuildVillage.`type`))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
