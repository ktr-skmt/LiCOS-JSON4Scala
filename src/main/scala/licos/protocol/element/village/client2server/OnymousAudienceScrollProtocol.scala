package licos.protocol.element.village.client2server

import java.net.URL

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonOnymousAudienceScroll
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
}

object OnymousAudienceScrollProtocol {

  def read(
      json:                 JsonOnymousAudienceScroll,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[OnymousAudienceScrollProtocol] = {
    VillageInfoFactory
      .create(villageInfoFromLobby, json.base)
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
