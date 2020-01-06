package licos.protocol.element.village.server2client

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonAnonymousAudienceChat
import play.api.libs.json.{JsValue, Json}

final case class AnonymousAudienceChatFromServerProtocol(village: VillageInfo, isMine: Boolean, text: String)
    extends Server2ClientVillageMessageProtocol {

  private lazy val json: Option[JsonAnonymousAudienceChat] = {
    server2logger.AnonymousAudienceChatFromServerProtocol(village, isMine, text, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object AnonymousAudienceChatFromServerProtocol {

  def read(
      json:                 JsonAnonymousAudienceChat,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[AnonymousAudienceChatFromServerProtocol] = {
    if (json.isFromServer) {
      VillageInfoFactory
        .create(villageInfoFromLobby, json.base)
        .map { village: VillageInfo =>
          AnonymousAudienceChatFromServerProtocol(
            village,
            json.isMine,
            json.text.`@value`
          )
        }
    } else {
      Option.empty[AnonymousAudienceChatFromServerProtocol]
    }
  }

}
