package licos.protocol.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonVote
import licos.json.element.village.iri.{BaseContext, Context, VoteContext, VoteMessage}
import licos.knowledge.{Character, ClientToServer, PrivateChannel, Role}
import licos.protocol.village.part.character.{RoleCharacterProtocol, SimpleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class VoteProtocol(
    village:                    Village,
    character:                  Character,
    role:                       Role,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) {

  val json: Option[JsonVote] = {
    if (village.isAvailable) {
      Option(
        new JsonVote(
          BaseProtocol(
            Seq[Context](BaseContext, VoteContext),
            VoteMessage,
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
            None,
            Option(TimestampGenerator.now),
            ClientToServer,
            PrivateChannel,
            extensionalDisclosureRange,
            Nil,
            Nil
          ).json,
          RoleCharacterProtocol(
            village.myCharacterOpt.get,
            village.id,
            village.language,
            village.myRoleOpt.get
          ).json,
          SimpleCharacterProtocol(
            character,
            village.id,
            village.language,
            role
          ).json(LiCOSOnline.stateVillage(village.id))
        )
      )
    } else {
      None
    }
  }

}
