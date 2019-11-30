package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.BuildVillageProtocol
import licos.protocol.engine.analysis.village.client2server.BuildVillageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.BuildVillage
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

class BuildVillageAE extends BuildVillageAnalysisEngine {
  override def process(box: VillageBOX, buildVillageProtocol: BuildVillageProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(BuildVillage.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
