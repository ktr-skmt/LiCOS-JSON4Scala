package licos.protocol.element.village.server2client

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.server2client.{JsonChatFromServer, JsonFlavorText}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import play.api.libs.json.{JsValue, Json}

final case class FlavorTextProtocol(village: VillageInfo, flavorText: Seq[ChatFromServerProtocol])
    extends Server2ClientVillageMessageProtocol {

  private lazy val json: Option[JsonFlavorText] = {
    server2logger.FlavorTextProtocol(village, flavorText, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def forLogger(extensionalDisclosureRange: Seq[StatusCharacterProtocol]): server2logger.FlavorTextProtocol = {
    server2logger.FlavorTextProtocol(
      village:                    VillageInfo,
      flavorText:                 Seq[ChatFromServerProtocol],
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
    )
  }
}

object FlavorTextProtocol {

  def read(json: JsonFlavorText, villageInfoFromLobby: VillageInfoFromLobby): Option[FlavorTextProtocol] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
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
