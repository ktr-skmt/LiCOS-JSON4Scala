package licos.protocol.element.village.client2server.server2logger

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.client2server.JsonBoard
import licos.json.element.village.iri.{BoardMessage, Contexts}
import licos.knowledge.{Character, ClientToServer, Data2Knowledge, PolarityMark, PrivateChannel, Role}
import licos.protocol.element.village.part.character.{
  RoleCharacterProtocol,
  SimpleCharacterProtocol,
  StatusCharacterProtocol
}
import licos.protocol.element.village.part.role.SimpleRoleProtocol
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

final case class BoardProtocol(
    village:                    VillageInfo,
    character:                  Character,
    role:                       Role,
    prediction:                 PolarityMark,
    myCharacter:                Character,
    myRole:                     Role,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonBoard] = {
    Some(
      new JsonBoard(
        BaseProtocol(
          Contexts.get(BoardMessage),
          BoardMessage,
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
        SimpleCharacterProtocol(
          character,
          village.id,
          village.language
        ).json(LiCOSOnline.stateVillage(village.id)),
        SimpleRoleProtocol(
          role,
          village.id,
          village.language
        ).json(LiCOSOnline.stateVillage(village.id)),
        prediction.label
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object BoardProtocol {

  def read(json: JsonBoard, villageInfoFromLobby: VillageInfoFromLobby): Option[BoardProtocol] = {
    VillageInfoFactory
      .create(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          prediction <- Data2Knowledge.polarityMarkOpt(json.prediction)
          character  <- Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
          role <- Data2Knowledge
            .roleOpt(
              json.role.name.en,
              village.composition.parse(json.role.name.en).map(_.numberOfPlayers).getOrElse(0)
            )
          myCharacter <- Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
          myRole      <- village.composition.parse(json.myCharacter.role.name.en)
        } yield {
          BoardProtocol(
            village,
            character,
            role,
            prediction,
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
