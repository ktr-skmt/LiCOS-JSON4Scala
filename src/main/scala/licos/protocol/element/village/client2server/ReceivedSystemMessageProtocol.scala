package licos.protocol.element.village.client2server

import licos.json.element.village.JsonPhase
import licos.json.element.village.receipt.JsonReceivedSystemMessage
import licos.protocol.element.village.VillageMessageProtocol

final case class ReceivedSystemMessageProtocol(phase: JsonPhase) extends VillageMessageProtocol {

  val json: Option[JsonReceivedSystemMessage] = {
    Option(new JsonReceivedSystemMessage(phase))
  }

}

object ReceivedSystemMessageProtocol {

  def read(json: JsonReceivedSystemMessage): Option[ReceivedSystemMessageProtocol] = {

  }

}
