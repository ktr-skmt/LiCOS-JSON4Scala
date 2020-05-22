package licos.protocol.element.village.server2client

import java.net.URL

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonOnymousAudienceChat
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceChatFromServerProtocol(
    village:       VillageInfo,
    isMine:        Boolean,
    text:          String,
    myAvatarName:  String,
    myAvatarImage: URL
) extends Server2ClientVillageMessageProtocol {

  private lazy val json: Option[JsonOnymousAudienceChat] = {
    server2logger.OnymousAudienceChatFromServerProtocol(village, isMine, text, myAvatarName, myAvatarImage, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object OnymousAudienceChatFromServerProtocol {

  def read(
      json:                 JsonOnymousAudienceChat,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[OnymousAudienceChatFromServerProtocol] = {
    if (json.isFromServer) {
      VillageInfoFactory
        .createOpt(villageInfoFromLobby, json.base)
        .map { village: VillageInfo =>
          OnymousAudienceChatFromServerProtocol(
            village,
            json.isMine,
            json.text.`@value`,
            json.avatar.name,
            new URL(json.avatar.image)
          )
        }
    } else {
      Option.empty[OnymousAudienceChatFromServerProtocol]
    }
  }

}
