package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.StarProtocol
import licos.protocol.engine.analysis.village.client2server.StarAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.Star
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class StarAE extends StarAnalysisEngine {
  override def process(box: VillageBOX, star: StarProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(Star.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
