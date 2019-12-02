package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.FlavorTextProtocol
import licos.protocol.engine.analysis.village.server2client.FlavorTextAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.FlavorText
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

class FlavorTextAE extends FlavorTextAnalysisEngine {
  override def process(box: VillageBOX, flavorText: FlavorTextProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(FlavorText.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
