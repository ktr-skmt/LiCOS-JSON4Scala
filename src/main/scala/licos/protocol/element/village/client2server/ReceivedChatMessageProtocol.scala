package licos.protocol.element.village.client2server

import java.time.OffsetDateTime
import java.util.UUID

import licos.json.element.village.receipt.JsonReceivedChatMessage

final case class ReceivedChatMessageProtocol(token: UUID, villageId: Long, serverTimestamp: OffsetDateTime, clientTimestamp: OffsetDateTime)
    extends Client2ServerVillageMessageProtocol {

  val json: Option[JsonReceivedChatMessage] = {
    Some(new JsonReceivedChatMessage(token.toString, villageId, serverTimestamp.toString, clientTimestamp.toString))
  }

}

object ReceivedChatMessageProtocol {

  def read(json: JsonReceivedChatMessage): Option[ReceivedChatMessageProtocol] = {
    Some(
      ReceivedChatMessageProtocol(
        UUID.fromString(json.token),
        json.villageId,
        OffsetDateTime.parse(json.serverTimestamp),
        OffsetDateTime.parse(json.clientTimestamp)
      )
    )
  }

}
