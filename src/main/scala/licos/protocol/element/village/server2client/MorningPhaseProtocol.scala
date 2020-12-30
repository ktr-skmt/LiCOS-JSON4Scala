package licos.protocol.element.village.server2client

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonCharacter
import licos.json.element.village.role.JsonRole
import licos.json.element.village.server2client.JsonPhase
import licos.json.element.village.{JsonBoardResult, JsonVotingResultSummary}
import licos.knowledge.{Character, Data2Knowledge, Morning, Role}
import licos.protocol.element.village.part.{
  BoardResultProtocol,
  UpdateProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.protocol.element.village.part.character.{CharacterProtocol, StatusCharacterProtocol}
import licos.protocol.element.village.part.role.RoleProtocol
import play.api.libs.json.{JsValue, Json}

final case class MorningPhaseProtocol(
    village:              VillageInfo,
    character:            Seq[CharacterProtocol],
    role:                 Seq[RoleProtocol],
    votingResultsSummary: Seq[VotingResultSummaryProtocol]
) extends Server2ClientVillageMessageProtocol {

  private lazy val json: Option[JsonPhase] = {
    server2logger.MorningPhaseProtocol(village, character, role, Nil, votingResultsSummary, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def forLogger(
      extensionalDisclosureRange: Seq[StatusCharacterProtocol],
      votingResultsDetail:        Seq[VotingResultDetailProtocol]
  ): server2logger.MorningPhaseProtocol = {
    server2logger.MorningPhaseProtocol(
      village:                    VillageInfo,
      character:                  Seq[CharacterProtocol],
      role:                       Seq[RoleProtocol],
      extensionalDisclosureRange: Seq[StatusCharacterProtocol],
      votingResultsSummary:       Seq[VotingResultSummaryProtocol],
      votingResultsDetail:        Seq[VotingResultDetailProtocol]
    )
  }
}

object MorningPhaseProtocol {

  def read(json: JsonPhase, villageInfoFromLobby: VillageInfoFromLobby): Option[MorningPhaseProtocol] = {
    import cats.implicits._
    if (json.base.phase === Morning.label && 1 < json.base.day) {
      VillageInfoFactory
        .createOpt(villageInfoFromLobby, json.base)
        .map { village: VillageInfo =>
          MorningPhaseProtocol(
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
            json.base.votingResultsSummary.toList.flatMap { summaries: Seq[JsonVotingResultSummary] =>
              summaries.flatMap { jsonVotingResultSummary: JsonVotingResultSummary =>
                Data2Knowledge
                  .characterOpt(
                    jsonVotingResultSummary.characterToPutToDeath.name.en,
                    jsonVotingResultSummary.characterToPutToDeath.id
                  )
                  .toList
                  .map { character: Character =>
                    VotingResultSummaryProtocol(
                      character,
                      jsonVotingResultSummary.numberOfVotes,
                      jsonVotingResultSummary.rankOfVotes,
                      village.id,
                      village.language
                    )
                  }
              }
            }
          )
        }
    } else {
      Option.empty[MorningPhaseProtocol]
    }
  }

}
