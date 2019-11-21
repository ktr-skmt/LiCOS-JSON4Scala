package licos.protocol.element.village.server2client.server2logger

import licos.entity.Village
import licos.json.element.village.JsonPhase
import licos.json.element.village.iri.{BaseContext, Context, SystemMessage, VotingResultContext}
import licos.knowledge.{Night, PrivateChannel, ServerToClient}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.protocol.element.village.part.character.{CharacterProtocol, StatusCharacterProtocol}
import licos.protocol.element.village.part.role.RoleProtocol
import licos.util.TimestampGenerator

final case class NightPhaseProtocol(
    village:                    Village,
    character:                  Seq[CharacterProtocol],
    role:                       Seq[RoleProtocol],
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends VillageMessageProtocol {

  val json: Option[JsonPhase] = {
    village.currentPhase = Night
    if (village.isAvailable) {
      Some(
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

object NightPhaseProtocol {

  def read(json: JsonPhase): Option[NightPhaseProtocol] = {

  }

}
