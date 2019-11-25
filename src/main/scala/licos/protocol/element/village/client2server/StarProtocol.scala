package licos.protocol.element.village.client2server

import java.time.OffsetDateTime

import licos.entity.Village
import licos.json.element.village.JsonStar
import play.api.libs.json.{JsValue, Json}

final case class StarProtocol(
    village:         Village,
    serverTimestamp: OffsetDateTime,
    clientTimestamp: OffsetDateTime,
    isMarked:        Boolean
) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonStar] = {
    server2logger.StarProtocol(village, serverTimestamp, clientTimestamp, isMarked, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonStar =>
      Json.toJson(j)
    }
  }

}

object StarProtocol {

  def read(json: JsonStar, village: Village): Option[StarProtocol] = {
    Some(
      StarProtocol(
        village,
        OffsetDateTime.parse(json.star.serverTimestamp),
        OffsetDateTime.parse(json.star.clientTimestamp),
        json.star.isMarked
      )
    )
  }

}
