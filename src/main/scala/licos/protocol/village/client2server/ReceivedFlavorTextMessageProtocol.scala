package licos.protocol.village.client2server

import licos.json.element.village.JsonFlavorText
import licos.json.element.village.receipt.JsonReceivedFlavorTextMessage

final case class ReceivedFlavorTextMessageProtocol(flavorText: JsonFlavorText) {

  val json: Option[JsonReceivedFlavorTextMessage] = {
    Option(new JsonReceivedFlavorTextMessage(flavorText))
  }

}
