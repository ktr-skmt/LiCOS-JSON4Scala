package licos.protocol.village.client2server

import java.time.OffsetDateTime

import licos.json.element.village.JsonStar
import licos.state.VillageState

final case class StarProtocol(state:           VillageState,
                              serverTimestamp: OffsetDateTime,
                              clientTimestamp: OffsetDateTime,
                              isMarked:        Boolean) {

  val json: Option[JsonStar] = {
    server2logger.StarProtocol(state, serverTimestamp, clientTimestamp, isMarked, Nil).json
  }

}
