package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceChat
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceChatFromClientProtocol(village: Village, text: String) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonOnymousAudienceChat] = {
    server2logger.OnymousAudienceChatFromClientProtocol(village, text, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object OnymousAudienceChatFromClientProtocol {

  def read(json: JsonOnymousAudienceChat, village: Village): Option[OnymousAudienceChatFromClientProtocol] = {
    if (!json.isFromServer) {
      Some(
        OnymousAudienceChatFromClientProtocol(
          village,
          json.text.`@value`
        )
      )
    } else {
      None
    }
  }

}
