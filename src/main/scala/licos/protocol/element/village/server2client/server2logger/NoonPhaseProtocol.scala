package licos.protocol.element.village.server2client.server2logger

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonBoardResult
import licos.json.element.village.character.{JsonCharacter, JsonStatusCharacter}
import licos.json.element.village.iri.{Contexts, SystemMessage}
import licos.json.element.village.role.JsonRole
import licos.json.element.village.server2client.JsonPhase
import licos.knowledge.{Data2Knowledge, PrivateChannel, Role, ServerToClient}
import licos.protocol.element.village.part.{
  BaseProtocol,
  BoardResultProtocol,
  ChatSettingsProtocol,
  UpdateProtocol,
  VillageProtocol
}
import licos.protocol.element.village.part.character.{CharacterProtocol, StatusCharacterProtocol}
import licos.protocol.element.village.part.role.RoleProtocol
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

final case class NoonPhaseProtocol(
    village:                    VillageInfo,
    character:                  Seq[CharacterProtocol],
    role:                       Seq[RoleProtocol],
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  val json: Option[JsonPhase] = {
    Some(
      new JsonPhase(
        BaseProtocol(
          Contexts.get(SystemMessage),
          SystemMessage,
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
          PrivateChannel,
          extensionalDisclosureRange,
          None,
          None
        ).json,
        character.map(_.json),
        role.map(_.json)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object NoonPhaseProtocol {

  def read(json: JsonPhase, villageInfoFromLobby: VillageInfoFromLobby): Option[NoonPhaseProtocol] = {
    VillageInfoFactory
      .create(villageInfoFromLobby, json.base)
      .map { village: VillageInfo =>
        NoonPhaseProtocol(
          village,
          json.character.flatMap { jsonCharacter: JsonCharacter =>
            for {
              character <- Data2Knowledge.characterOpt(jsonCharacter.name.en, jsonCharacter.id).toList
              phase     <- Data2Knowledge.phaseOpt(jsonCharacter.update.phase).toList
              status    <- Data2Knowledge.statusOpt(jsonCharacter.status).toList
            } yield {
              CharacterProtocol(
                character,
                village.id,
                village.language,
                jsonCharacter.isMine,
                status,
                UpdateProtocol(
                  phase,
                  jsonCharacter.update.day
                ),
                jsonCharacter.isAChoice
              )
            }
          },
          json.role.flatMap { jsonRole: JsonRole =>
            Data2Knowledge.roleOpt(jsonRole.name.en, jsonRole.numberOfPlayers).toList.map {
              role: Role =>
                RoleProtocol(
                  role,
                  jsonRole.isMine,
                  jsonRole.numberOfPlayers,
                  jsonRole.board.flatMap {
                    jsonBoardResult: JsonBoardResult =>
                      for {
                        character <- Data2Knowledge
                          .characterOpt(jsonBoardResult.character.name.en, jsonBoardResult.character.id)
                          .toList
                        polarity <- Data2Knowledge.polarityMarkOpt(jsonBoardResult.polarity).toList
                        phase    <- Data2Knowledge.phaseOpt(jsonBoardResult.phase).toList
                      } yield {
                        BoardResultProtocol(
                          character,
                          polarity,
                          phase,
                          jsonBoardResult.day,
                          village.id,
                          village.language
                        )
                      }
                  },
                  village.id,
                  village.language
                )
            }
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
