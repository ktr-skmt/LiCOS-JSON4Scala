package licos.protocol.element.village.client2server.server2logger

import java.time.OffsetDateTime

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.client2server.JsonStar
import licos.json.element.village.iri.{Contexts, StarMessage}
import licos.knowledge.{Character, ClientToServer, Data2Knowledge, PrivateChannel, Role}
import licos.protocol.element.village.part.character.{RoleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, StarInfoProtocol, VillageProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

final case class StarProtocol(
    village:                    VillageInfo,
    serverTimestamp:            OffsetDateTime,
    clientTimestamp:            OffsetDateTime,
    isMarked:                   Boolean,
    myCharacter:                Character,
    myRole:                     Role,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonStar] = {
    Some(
      new JsonStar(
        BaseProtocol(
          Contexts.get(StarMessage),
          StarMessage,
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
          None,
          Some(TimestampGenerator.now),
          ClientToServer,
          PrivateChannel,
          extensionalDisclosureRange,
          None,
          None
        ).json,
        RoleCharacterProtocol(
          myCharacter,
          myRole,
          village.id,
          village.language
        ).json,
        StarInfoProtocol(
          village.id,
          village.token,
          serverTimestamp,
          clientTimestamp,
          isMarked
        ).json
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object StarProtocol {

  def read(json: JsonStar, villageInfoFromLobby: VillageInfoFromLobby): Option[StarProtocol] = {
    VillageInfoFactory
      .create(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          myCharacter <- Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
          myRole      <- village.composition.parse(json.myCharacter.role.name.en)
        } yield {
          StarProtocol(
            village,
            OffsetDateTime.parse(json.star.serverTimestamp),
            OffsetDateTime.parse(json.star.clientTimestamp),
            json.star.isMarked,
            myCharacter,
            myRole,
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

}
