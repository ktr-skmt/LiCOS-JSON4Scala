package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ErrorFromClientProtocol
import licos.protocol.engine.analysis.village.client2server.ErrorFromClientAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.ErrorFromClient
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

final class ErrorFromClientAE extends ErrorFromClientAnalysisEngine {
  override def process(box: VillageBOX, errorFromClient: ErrorFromClientProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(ErrorFromClient.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
