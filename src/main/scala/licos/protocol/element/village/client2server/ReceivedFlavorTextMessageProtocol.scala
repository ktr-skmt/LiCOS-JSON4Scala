package licos.protocol.element.village.client2server

import java.util.UUID

import licos.json.element.village.receipt.JsonReceivedFlavorTextMessage
import licos.knowledge.{Data2Knowledge, Phase}

final case class ReceivedFlavorTextMessageProtocol(token: UUID, villageId: Long, phase: Phase, day: Int)
    extends Client2ServerVillageMessageProtocol {

  val json: Option[JsonReceivedFlavorTextMessage] = {
    Some(new JsonReceivedFlavorTextMessage(token.toString, villageId, phase.label, day))
  }

}

object ReceivedFlavorTextMessageProtocol {

  def read(json: JsonReceivedFlavorTextMessage): Option[ReceivedFlavorTextMessageProtocol] = {
    Data2Knowledge.phaseOpt(json.phase) map { phase: Phase =>
      ReceivedFlavorTextMessageProtocol(UUID.fromString(json.token), json.villageId, phase, json.day)
    }
  }

}
