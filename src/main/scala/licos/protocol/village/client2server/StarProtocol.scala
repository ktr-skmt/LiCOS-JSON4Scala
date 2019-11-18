package licos.protocol.village.client2server

import java.time.OffsetDateTime

import licos.entity.Village
import licos.json.element.village.JsonStar

final case class StarProtocol(
    village:         Village,
    serverTimestamp: OffsetDateTime,
    clientTimestamp: OffsetDateTime,
    isMarked:        Boolean
) {

  val json: Option[JsonStar] = {
    server2logger.StarProtocol(village, serverTimestamp, clientTimestamp, isMarked, Nil).json
  }

}
