package licos.protocol.element.village.server2client

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.server2client.{JsonChatFromServer, JsonFlavorText}
import play.api.libs.json.{JsValue, Json}

final case class FlavorTextProtocol(village: VillageInfo, flavorText: Seq[ChatFromServerProtocol])
    extends Server2ClientVillageMessageProtocol {

  private lazy val json: Option[JsonFlavorText] = {
    server2logger.FlavorTextProtocol(village, flavorText, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object FlavorTextProtocol {

  def read(json: JsonFlavorText, villageInfoFromLobby: VillageInfoFromLobby): Option[FlavorTextProtocol] = {
    VillageInfoFactory
      .create(villageInfoFromLobby, json.base)
      .map { village: VillageInfo =>
        FlavorTextProtocol(
          village,
          json.flavorText.flatMap { jsonChatFromServer: JsonChatFromServer =>
            ChatFromServerProtocol.read(jsonChatFromServer, villageInfoFromLobby).toList
          }
        )
      }
  }

}
