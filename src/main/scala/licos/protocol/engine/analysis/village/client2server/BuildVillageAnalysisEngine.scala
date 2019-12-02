package licos.protocol.engine.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.BuildVillageProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait BuildVillageAnalysisEngine extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, buildVillage: BuildVillageProtocol): Try[VillageMessageProtocol]
}

object BuildVillageAnalysisEngine {
  val name: String = "village.client2server.BuildVillage"
}
