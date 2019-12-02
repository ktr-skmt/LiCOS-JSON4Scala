package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReadyProtocol
import licos.protocol.engine.analysis.village.client2server.ReadyAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.Ready
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class ReadyAE extends ReadyAnalysisEngine {
  override def process(box: VillageBOX, readyProtocol: ReadyProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(Ready.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
