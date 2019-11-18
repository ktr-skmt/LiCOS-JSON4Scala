package licos.protocol.village.server2client.server2logger

import licos.entity.Village
import licos.json.element.village.JsonGameResult
import licos.json.element.village.iri.{BaseContext, Context, SystemMessage, VotingResultContext}
import licos.knowledge.{PublicChannel, Result, ServerToClient}
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.protocol.village.part.character.{ResultCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.role.ResultRoleProtocol
import licos.util.TimestampGenerator

final case class GameResultProtocol(
    village:                    Village,
    character:                  Seq[ResultCharacterProtocol],
    role:                       Seq[ResultRoleProtocol],
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) {

  val json: Option[JsonGameResult] = {
    village.currentPhase = Result
    if (village.isAvailable) {
      Option(
        new JsonGameResult(
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
            PublicChannel,
            extensionalDisclosureRange,
            Nil,
            Nil
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
