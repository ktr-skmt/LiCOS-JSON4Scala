package licos.protocol.element.village.client2server

import java.net.URL

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonOnymousAudienceScroll
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceScrollProtocol(
    village:       VillageInfo,
    nodeId:        String,
    scrollTop:     Int,
    scrollHeight:  Int,
    offsetHeight:  Int,
    myAvatarName:  String,
    myAvatarImage: URL
) extends Client2ServerVillageMessageProtocol {

  private lazy val json: Option[JsonOnymousAudienceScroll] = {
    server2logger
      .OnymousAudienceScrollProtocol(
        village,
        nodeId,
        scrollTop,
        scrollHeight,
        offsetHeight,
        myAvatarName,
        myAvatarImage,
        Nil
      )
      .json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def forLogger(
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
  ): server2logger.OnymousAudienceScrollProtocol = {
    server2logger.OnymousAudienceScrollProtocol(
      village:                    VillageInfo,
      nodeId:                     String,
      scrollTop:                  Int,
      scrollHeight:               Int,
      offsetHeight:               Int,
      myAvatarName:               String,
      myAvatarImage:              URL,
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
    )
  }
}

object OnymousAudienceScrollProtocol {

  def read(
      json:                 JsonOnymousAudienceScroll,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[OnymousAudienceScrollProtocol] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .map { village: VillageInfo =>
        OnymousAudienceScrollProtocol(
          village,
          json.nodeId,
          json.scrollTop,
          json.scrollHeight,
          json.offsetHeight,
          json.avatar.name,
          new URL(json.avatar.image)
        )
      }
  }
}
