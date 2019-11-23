package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceScroll
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceScrollProtocol(village:      Village,
                                               nodeId:       String,
                                               scrollTop:    Int,
                                               scrollHeight: Int,
                                               offsetHeight: Int) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonOnymousAudienceScroll] = {
    server2logger.OnymousAudienceScrollProtocol(village, nodeId, scrollTop, scrollHeight, offsetHeight, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object OnymousAudienceScrollProtocol {

  def read(json: JsonOnymousAudienceScroll, village: Village): Option[OnymousAudienceScrollProtocol] = {
    Some(
      OnymousAudienceScrollProtocol(
        village,
        json.nodeId,
        json.scrollTop,
        json.scrollHeight,
        json.offsetHeight
      )
    )
  }

}
