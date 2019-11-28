package protocol.engine.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ChatFromClientProtocol
import licos.protocol.engine.analysis.village.client2server.ChatFromClientAnalysisEngine
import licos.protocol.engine.processing.VillageBOX
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.ChatFromClient
import protocol.engine.village.{NoVillageBOXException, VillageBox}

import scala.util.{Failure, Success, Try}

class ChatFromClientAE extends ChatFromClientAnalysisEngine {
  override def process(box: VillageBOX, chatFromClient: ChatFromClientProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(ChatFromClient.`type`))
      case _ => Failure(new NoVillageBOXException())
    }
  }
}
