package licos.protocol.element.village.server2client.server2logger

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{Contexts, FlavorTextMessage}
import licos.json.element.village.server2client.{JsonChatFromServer, JsonFlavorText}
import licos.knowledge.{Data2Knowledge, PublicChannel, ServerToClient}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

final case class FlavorTextProtocol(
    village:                    VillageInfo,
    flavorText:                 Seq[licos.protocol.element.village.server2client.ChatFromServerProtocol],
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  lazy val json: Option[JsonFlavorText] = {
    Some(
      new JsonFlavorText(
        BaseProtocol(
          Contexts.get(FlavorTextMessage),
          FlavorTextMessage,
          VillageProtocol(
            village.id,
            village.name,
            village.composition.totalNumberOfPlayers,
            village.language,
            ChatSettingsProtocol(
              village.id,
              village.maxNumberOfChatMessages,
              village.maxLengthOfUnicodeCodePoints
            )
          ),
          village.token,
          village.phase,
          village.day,
          village.phaseTimeLimit,
          village.phaseStartTime,
          Some(TimestampGenerator.now),
          None,
          ServerToClient,
          PublicChannel,
          extensionalDisclosureRange,
          None,
          None
        ).json,
        flavorText.flatMap(_.json.toList)
      )
    )
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
            licos.protocol.element.village.server2client.ChatFromServerProtocol
              .read(jsonChatFromServer, villageInfoFromLobby)
              .toList
          },
          json.base.extensionalDisclosureRange.flatMap { jsonStatusCharacter: JsonStatusCharacter =>
            for {
              character  <- Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id).toList
              role       <- village.composition.parse(jsonStatusCharacter.role.name.en).toList
              status     <- Data2Knowledge.statusOpt(jsonStatusCharacter.status).toList
              playerType <- Data2Knowledge.architectureOpt(jsonStatusCharacter.playerType).toList
            } yield {
              StatusCharacterProtocol(
                character,
                role,
                status,
                playerType,
                village.id,
                village.language
              )
            }
          }
        )
      }
  }

}
