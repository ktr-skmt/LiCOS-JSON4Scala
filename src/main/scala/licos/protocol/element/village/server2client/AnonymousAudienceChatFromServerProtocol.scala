package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonAnonymousAudienceChat
import play.api.libs.json.{JsValue, Json}

final case class AnonymousAudienceChatFromServerProtocol(village: Village, isMine: Boolean, text: String)
    extends Server2ClientVillageMessageProtocol {

  private val json: Option[JsonAnonymousAudienceChat] = {
    server2logger.AnonymousAudienceChatFromServerProtocol(village, isMine, text, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object AnonymousAudienceChatFromServerProtocol {

  def read(json: JsonAnonymousAudienceChat, village: Village): Option[AnonymousAudienceChatFromServerProtocol] = {
    if (json.isFromServer) {
      Some(
        AnonymousAudienceChatFromServerProtocol(
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
