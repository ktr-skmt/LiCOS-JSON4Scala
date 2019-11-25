package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonScroll
import play.api.libs.json.{JsValue, Json}

final case class ScrollProtocol(
    village:      Village,
    nodeId:       String,
    scrollTop:    Int,
    scrollHeight: Int,
    offsetHeight: Int
) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonScroll] = {
    server2logger.ScrollProtocol(village, nodeId, scrollTop, scrollHeight, offsetHeight, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonScroll =>
      Json.toJson(j)
    }
  }

}

object ScrollProtocol {

  def read(json: JsonScroll, village: Village): Option[ScrollProtocol] = {
    Some(
      ScrollProtocol(
        village,
        json.nodeId,
        json.scrollTop,
        json.scrollHeight,
        json.offsetHeight
      )
    )
  }

}
