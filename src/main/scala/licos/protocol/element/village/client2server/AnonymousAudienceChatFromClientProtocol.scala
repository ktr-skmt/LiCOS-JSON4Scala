package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonAnonymousAudienceChat
import play.api.libs.json.{JsValue, Json}

final case class AnonymousAudienceChatFromClientProtocol(village: VillageInfo, text: String)
    extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonAnonymousAudienceChat] = {
    server2logger.AnonymousAudienceChatFromClientProtocol(village, text, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object AnonymousAudienceChatFromClientProtocol {

  def read(
      json:                 JsonAnonymousAudienceChat,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[AnonymousAudienceChatFromClientProtocol] = {
    if (!json.isFromServer) {
      VillageInfoFactory
        .create(villageInfoFromLobby, json.base)
        .map { village: VillageInfo =>
          AnonymousAudienceChatFromClientProtocol(
            village,
            json.text.`@value`
          )
        }
    } else {
      None
    }
  }

}
