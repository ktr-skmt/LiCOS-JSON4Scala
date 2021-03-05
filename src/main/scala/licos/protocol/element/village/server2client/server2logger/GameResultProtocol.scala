package licos.protocol.element.village.server2client.server2logger

import java.net.URL
import java.time.OffsetDateTime

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.{JsonResultCharacter, JsonSimpleCharacter, JsonStatusCharacter}
import licos.json.element.village.iri.{Contexts, SystemMessage}
import licos.json.element.village.role.JsonResultRole
import licos.json.element.village.server2client.JsonGameResult
import licos.knowledge.{Character, Data2Knowledge, PublicChannel, Role, ServerToClient}
import licos.protocol.element.village.part.{
  BaseProtocol,
  ChatSettingsProtocol,
  VillageProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.protocol.element.village.part.character.{
  ResultCharacterProtocol,
  SimpleCharacterProtocol,
  StatusCharacterProtocol
}
import licos.protocol.element.village.part.role.ResultRoleProtocol
import licos.protocol.element.village.server2client.{GameResultProtocol => SimpleGameResultProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

final case class GameResultProtocol(
    village:                    VillageInfo,
    character:                  Seq[ResultCharacterProtocol],
    role:                       Seq[ResultRoleProtocol],
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  lazy val json: Option[JsonGameResult] = {
    Some(
      new JsonGameResult(
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
          Option.empty[OffsetDateTime],
          ServerToClient,
          PublicChannel,
          extensionalDisclosureRange,
          Option.empty[Seq[VotingResultSummaryProtocol]],
          Option.empty[Seq[VotingResultDetailProtocol]]
        ).json,
        character.map(_.json),
        role.map(_.json)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def simpleProtocol: SimpleGameResultProtocol = SimpleGameResultProtocol(
    village:   VillageInfo,
    character: Seq[ResultCharacterProtocol],
    role:      Seq[ResultRoleProtocol]
  )

}

object GameResultProtocol {

  def read(json: JsonGameResult, villageInfoFromLobby: VillageInfoFromLobby): Option[GameResultProtocol] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .map { village: VillageInfo =>
        GameResultProtocol(
          village,
          json.character.flatMap { jsonResultCharacter: JsonResultCharacter =>
            for {
              character <- Data2Knowledge.characterOpt(jsonResultCharacter.name.en, jsonResultCharacter.id).toList
              role      <- village.composition.parse(jsonResultCharacter.role.name.en).toList
              status    <- Data2Knowledge.statusOpt(jsonResultCharacter.status).toList
              result    <- Data2Knowledge.outcomeOpt(jsonResultCharacter.result).toList
            } yield {
              ResultCharacterProtocol(
                character,
                jsonResultCharacter.isMine,
                role,
                status,
                result,
                village.token,
                jsonResultCharacter.avatar.name,
                new URL(jsonResultCharacter.avatar.image),
                village.id,
                village.language
              )
            }
          },
          json.role.flatMap { jsonResultRole: JsonResultRole =>
            Data2Knowledge.roleOpt(jsonResultRole.name.en, jsonResultRole.numberOfPlayers).toList.map { role: Role =>
              ResultRoleProtocol(
                role,
                jsonResultRole.isMine,
                jsonResultRole.character.flatMap { jsonSimpleCharacter: JsonSimpleCharacter =>
                  Data2Knowledge.characterOpt(jsonSimpleCharacter.name.en, jsonSimpleCharacter.id).toList.map {
                    character: Character =>
                      SimpleCharacterProtocol(
                        character,
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
