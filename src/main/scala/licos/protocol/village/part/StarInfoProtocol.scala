package licos.protocol.village.part

import java.time.OffsetDateTime
import java.util.UUID

import licos.json.element.village.JsonStarInfo
import licos.json.element.village.iri.StarContext
import licos.util.LiCOSOnline

final case class StarInfoProtocol(villageId:       Long,
                                  token:           UUID,
                                  serverTimestamp: OffsetDateTime,
                                  clientTimestamp: OffsetDateTime,
                                  isMarked:        Boolean) {

  val json: JsonStarInfo = {
    JsonStarInfo(
      StarContext.iri,
      LiCOSOnline.state(villageId, "star"),
      token.toString,
      serverTimestamp.toString,
      clientTimestamp.toString,
      isMarked
    )
  }

}
