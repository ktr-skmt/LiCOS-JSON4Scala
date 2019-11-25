package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonAnonymousAudienceChat
import play.api.libs.json.{JsValue, Json}

final case class AnonymousAudienceChatFromClientProtocol(village: Village, text: String)
    extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonAnonymousAudienceChat] = {
    server2logger.AnonymousAudienceChatFromClientProtocol(village, text, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object AnonymousAudienceChatFromClientProtocol {

  def read(json: JsonAnonymousAudienceChat, village: Village): Option[AnonymousAudienceChatFromClientProtocol] = {
    if (village.isAvailable && !json.isFromServer) {
      Some(
        AnonymousAudienceChatFromClientProtocol(
          village,
          json.text.`@value`
        )
      )
    } else {
      None
    }
  }

}
