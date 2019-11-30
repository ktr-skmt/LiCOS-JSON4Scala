package protocol.engine.village.analysis.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.ChatFromServerProtocol
import licos.protocol.engine.analysis.village.server2client.server2logger.ChatFromServerAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.server2logger.ChatFromServer
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

class ChatFromServerAE extends ChatFromServerAnalysisEngine {
  override def process(box: VillageBOX, chatFromServer: ChatFromServerProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(ChatFromServer.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
