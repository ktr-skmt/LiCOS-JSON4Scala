package licos.protocol.element.village.server2client.server2logger

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.{JsonCharacter, JsonStatusCharacter}
import licos.json.element.village.{JsonBoardResult, JsonVotingResultDetail, JsonVotingResultSummary}
import licos.json.element.village.iri.{Contexts, SystemMessage}
import licos.json.element.village.role.JsonRole
import licos.json.element.village.server2client.JsonPhase
import licos.knowledge.{Character, Data2Knowledge, Phase, PolarityMark, PrivateChannel, Role, ServerToClient, Status}
import licos.protocol.element.village.part.{
  BaseProtocol,
  BoardResultProtocol,
  ChatSettingsProtocol,
  UpdateProtocol,
  VillageProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.protocol.element.village.part.character.{
  CharacterProtocol,
  SimpleCharacterProtocol,
  StatusCharacterProtocol
}
import licos.protocol.element.village.part.role.RoleProtocol
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class MorningPhaseProtocol(
    village:                    VillageInfo,
    character:                  Seq[CharacterProtocol],
    role:                       Seq[RoleProtocol],
    extensionalDisclosureRange: Seq[StatusCharacterProtocol],
    votingResultsSummary:       Seq[VotingResultSummaryProtocol],
    votingResultsDetail:        Seq[VotingResultDetailProtocol]
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
            village.cast.totalNumberOfPlayers,
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
          Option(TimestampGenerator.now),
          None,
          ServerToClient,
          PrivateChannel,
          extensionalDisclosureRange,
          Option(votingResultsSummary),
          Option(votingResultsDetail)
        ).json,
        character.map(_.json),
        role.map(_.json)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonPhase =>
      Json.toJson(j)
    }
  }

}

object MorningPhaseProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.OptionPartial",
      "org.wartremover.warts.MutableDataStructures"
    )
  )
  def read(json: JsonPhase, villageInfoFromLobby: VillageInfoFromLobby): Option[MorningPhaseProtocol] = {
    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
        val characterBuffer = ListBuffer.empty[CharacterProtocol]
        val roleBuffer      = ListBuffer.empty[RoleProtocol]

        json.character foreach { jsonCharacter: JsonCharacter =>
          val characterOpt: Option[Character] = Data2Knowledge.characterOpt(jsonCharacter.name.en, jsonCharacter.id)
          val phaseOpt:     Option[Phase]     = Data2Knowledge.phaseOpt(jsonCharacter.update.phase)
          if (characterOpt.nonEmpty && phaseOpt.nonEmpty) {
            val statusOpt: Option[Status] = Data2Knowledge.statusOpt(jsonCharacter.status)
            if (statusOpt.nonEmpty) {
              characterBuffer += CharacterProtocol(
                characterOpt.get,
                village.id,
                village.language,
                jsonCharacter.isMine,
                statusOpt.get,
                UpdateProtocol(
                  phaseOpt.get,
                  jsonCharacter.update.day
                ),
                jsonCharacter.isAChoice
              )
            }
          }
        }

        json.role foreach { jsonRole: JsonRole =>
          val roleOpt: Option[Role] = Data2Knowledge.roleOpt(jsonRole.name.en, jsonRole.numberOfPlayers)
          if (roleOpt.nonEmpty) {
            val boardResultBuffer = ListBuffer.empty[BoardResultProtocol]
            jsonRole.board foreach { jsonBoardResult: JsonBoardResult =>
              val characterOpt: Option[Character] =
                Data2Knowledge.characterOpt(jsonBoardResult.character.name.en, jsonBoardResult.character.id)
              val polarityOpt: Option[PolarityMark] = Data2Knowledge.polarityMarkOpt(jsonBoardResult.polarity)
              val phaseOpt:    Option[Phase]        = Data2Knowledge.phaseOpt(jsonBoardResult.phase)

              if (characterOpt.nonEmpty && polarityOpt.nonEmpty && phaseOpt.nonEmpty) {
                boardResultBuffer += BoardResultProtocol(
                  characterOpt.get,
                  polarityOpt.get,
                  phaseOpt.get,
                  jsonBoardResult.day,
                  village.id,
                  village.language
                )
              }
            }

            roleBuffer += RoleProtocol(
              roleOpt.get,
              jsonRole.isMine,
              jsonRole.numberOfPlayers,
              boardResultBuffer.result,
              village.id,
              village.language
            )
          }
        }

        val votingResultSummaryBuffer = ListBuffer.empty[VotingResultSummaryProtocol]
        json.base.votingResultsSummary foreach { summaries: Seq[JsonVotingResultSummary] =>
          summaries foreach { jsonVotingResultSummary: JsonVotingResultSummary =>
            val characterOpt: Option[Character] = Data2Knowledge.characterOpt(
              jsonVotingResultSummary.characterToPutToDeath.name.en,
              jsonVotingResultSummary.characterToPutToDeath.id
            )
            if (characterOpt.nonEmpty) {
              votingResultSummaryBuffer += VotingResultSummaryProtocol(
                characterOpt.get,
                jsonVotingResultSummary.numberOfVotes,
                jsonVotingResultSummary.rankOfVotes,
                village.id,
                village.language
              )
            }
          }
        }

        val votingResultDetailBuffer = ListBuffer.empty[VotingResultDetailProtocol]
        json.base.votingResultsDetails foreach { details: Seq[JsonVotingResultDetail] =>
          details foreach { jsonVotingResultDetail: JsonVotingResultDetail =>
            val sourceCharacterOpt: Option[Character] = Data2Knowledge
              .characterOpt(jsonVotingResultDetail.sourceCharacter.name.en, jsonVotingResultDetail.sourceCharacter.id)
            val targetCharacterOpt: Option[Character] = Data2Knowledge
              .characterOpt(jsonVotingResultDetail.targetCharacter.name.en, jsonVotingResultDetail.targetCharacter.id)
            if (sourceCharacterOpt.nonEmpty && targetCharacterOpt.nonEmpty) {
              votingResultDetailBuffer += VotingResultDetailProtocol(
                SimpleCharacterProtocol(
                  sourceCharacterOpt.get,
                  village.id,
                  village.language
                ),
                SimpleCharacterProtocol(
                  targetCharacterOpt.get,
                  village.id,
                  village.language
                ),
                village.id
              )
            }
          }
        }

        val statusCharacterBuffer = ListBuffer.empty[StatusCharacterProtocol]
        json.base.extensionalDisclosureRange foreach { jsonStatusCharacter: JsonStatusCharacter =>
          val characterOpt: Option[Character] =
            Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id)
          val roleOpt:   Option[Role]   = village.cast.parse(jsonStatusCharacter.role.name.en)
          val statusOpt: Option[Status] = Data2Knowledge.statusOpt(jsonStatusCharacter.status)
          if (characterOpt.nonEmpty && roleOpt.nonEmpty && statusOpt.nonEmpty) {
            statusCharacterBuffer += StatusCharacterProtocol(
              characterOpt.get,
              roleOpt.get,
              statusOpt.get,
              jsonStatusCharacter.isHumanPlayer,
              village.id,
              village.language
            )
          }
        }

        Some(
          MorningPhaseProtocol(
            village,
            characterBuffer.result,
            roleBuffer.result,
            statusCharacterBuffer.result,
            votingResultSummaryBuffer.result,
            votingResultDetailBuffer.result
          )
        )
      case None => None
    }
  }

}
