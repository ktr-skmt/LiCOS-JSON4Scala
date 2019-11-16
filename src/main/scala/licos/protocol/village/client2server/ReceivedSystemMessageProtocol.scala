package licos.protocol.village.client2server

import licos.json.element.village.JsonPhase
import licos.json.element.village.receipt.JsonReceivedSystemMessage

final case class ReceivedSystemMessageProtocol(phase: JsonPhase) {

  val json: Option[JsonReceivedSystemMessage] = {
    Option(new JsonReceivedSystemMessage(phase))
  }

}
