package licos.protocol.village.server2client.server2logger

import licos.entity.Village
import licos.json.element.village.JsonPhase
import licos.json.element.village.iri.{BaseContext, Context, SystemMessage, VotingResultContext}
import licos.knowledge.{Morning, PrivateChannel, ServerToClient}
import licos.protocol.village.part.{
  BaseProtocol,
  ChatSettingsProtocol,
  VillageProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.protocol.village.part.character.{CharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.role.RoleProtocol
import licos.util.TimestampGenerator

final case class MorningPhaseProtocol(
    village:                    Village,
    character:                  Seq[CharacterProtocol],
    role:                       Seq[RoleProtocol],
    extensionalDisclosureRange: Seq[StatusCharacterProtocol],
    votingResultsSummary:       Seq[VotingResultSummaryProtocol],
    votingResultsDetail:        Seq[VotingResultDetailProtocol]
) {

  val json: Option[JsonPhase] = {
    village.currentPhase = Morning
    if (village.isAvailable) {
      Option(
        new JsonPhase(
          BaseProtocol(
            Seq[Context](BaseContext, VotingResultContext),
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
            village.tokenOpt.get,
            village.currentPhase,
            village.currentDay,
            village.currentPhase.timeLimit(village.currentDay, village.numberOfAlivePlayers).get,
            village.phaseStartTimeOpt.get,
            Option(TimestampGenerator.now),
            None,
            ServerToClient,
            PrivateChannel,
            extensionalDisclosureRange,
            votingResultsSummary,
            votingResultsDetail
          ).json,
          character.map(_.json),
          role.map(_.json)
        )
      )
    } else {
      None
    }
  }

}
