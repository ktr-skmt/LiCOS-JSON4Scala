package licos.protocol.element.village.client2server

import licos.json.element.village.JsonFlavorText
import licos.json.element.village.receipt.JsonReceivedFlavorTextMessage
import licos.protocol.element.village.VillageMessageProtocol

final case class ReceivedFlavorTextMessageProtocol(flavorText: JsonFlavorText) extends VillageMessageProtocol {

  val json: Option[JsonReceivedFlavorTextMessage] = {
    Option(new JsonReceivedFlavorTextMessage(flavorText))
  }

}

object ReceivedFlavorTextMessageProtocol {

  def read(json: JsonReceivedFlavorTextMessage): Option[ReceivedFlavorTextMessageProtocol] = {

  }

}
