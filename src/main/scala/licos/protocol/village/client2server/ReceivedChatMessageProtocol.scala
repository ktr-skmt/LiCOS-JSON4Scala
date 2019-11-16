package licos.protocol.village.client2server

import java.time.OffsetDateTime

import licos.json.element.village.JsonChatFromServer
import licos.json.element.village.receipt.JsonReceivedChatMessage

final case class ReceivedChatMessageProtocol(chat: JsonChatFromServer, clientTimestamp: OffsetDateTime) {

  val json: Option[JsonReceivedChatMessage] = {
    Option(new JsonReceivedChatMessage(
      chat,
      OffsetDateTime.now.toString))
  }

}
