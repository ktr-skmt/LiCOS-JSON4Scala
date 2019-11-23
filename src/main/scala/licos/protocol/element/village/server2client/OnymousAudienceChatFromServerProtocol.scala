package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceChat
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceChatFromServerProtocol(village: Village, isMine: Boolean, text: String)
    extends Server2ClientVillageMessageProtocol {

  private val json: Option[JsonOnymousAudienceChat] = {
    server2logger.OnymousAudienceChatFromServerProtocol(village, isMine, text, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object OnymousAudienceChatFromServerProtocol {

  def read(json: JsonOnymousAudienceChat, village: Village): Option[OnymousAudienceChatFromServerProtocol] = {
    if (json.isFromServer) {
      Some(
        OnymousAudienceChatFromServerProtocol(
          village,
          json.isMine,
          json.text.`@value`
        )
      )
    } else {
      None
    }
  }

}
