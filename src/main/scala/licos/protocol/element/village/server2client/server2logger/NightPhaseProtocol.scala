package licos.protocol.element.village.server2client.server2logger

import java.time.OffsetDateTime

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
  VillageProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.protocol.element.village.part.character.{CharacterProtocol, StatusCharacterProtocol}
import licos.protocol.element.village.part.role.RoleProtocol
import licos.protocol.element.village.server2client.{NightPhaseProtocol => SimpleNightPhaseProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

final case class NightPhaseProtocol(
    village:                    VillageInfo,
    character:                  Seq[CharacterProtocol],
    role:                       Seq[RoleProtocol],
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  lazy val json: Option[JsonPhase] = {
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
          Option.empty[OffsetDateTime],
          ServerToClient,
          PrivateChannel,
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

  def simpleProtocol: SimpleNightPhaseProtocol = SimpleNightPhaseProtocol(
    village:   VillageInfo,
    character: Seq[CharacterProtocol],
    role:      Seq[RoleProtocol]
  )
}

object NightPhaseProtocol {

  def read(json: JsonPhase, villageInfoFromLobby: VillageInfoFromLobby): Option[NightPhaseProtocol] = {

    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .map { village: VillageInfo =>
        NightPhaseProtocol(
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
            Data2Knowledge.roleOpt(jsonRole.name.en, jsonRole.numberOfPlayers).toList.map { role: Role =>
              RoleProtocol(
                role,
                jsonRole.isMine,
                jsonRole.numberOfPlayers,
                jsonRole.board.flatMap { jsonBoardResult: JsonBoardResult =>
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
