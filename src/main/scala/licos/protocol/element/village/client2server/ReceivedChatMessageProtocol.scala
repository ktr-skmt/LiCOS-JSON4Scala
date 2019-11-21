package licos.protocol.element.village.client2server

import java.time.OffsetDateTime

import licos.json.element.village.JsonChatFromServer
import licos.json.element.village.receipt.JsonReceivedChatMessage
import licos.protocol.element.village.VillageMessageProtocol

final case class ReceivedChatMessageProtocol(chat: JsonChatFromServer, clientTimestamp: OffsetDateTime)
    extends VillageMessageProtocol {

  val json: Option[JsonReceivedChatMessage] = {
    Some(new JsonReceivedChatMessage(chat, OffsetDateTime.now.toString))
  }

}

object ReceivedChatMessageProtocol {

  def read(json: JsonReceivedChatMessage): Option[ReceivedChatMessageProtocol] = {
    Some(ReceivedChatMessageProtocol(

    ))
  }

}
