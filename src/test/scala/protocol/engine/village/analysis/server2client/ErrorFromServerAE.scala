package protocol.engine.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.ErrorFromServerProtocol
import licos.protocol.engine.analysis.village.server2client.ErrorFromServerAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.ErrorFromServer
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

class ErrorFromServerAE extends ErrorFromServerAnalysisEngine {
  override def process(box: VillageBOX, errorFromServer: ErrorFromServerProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(ErrorFromServer.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
