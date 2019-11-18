package licos.protocol.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonBoard
import licos.json.element.village.iri.{BaseContext, BoardContext, BoardMessage, Context}
import licos.knowledge.{Character, ClientToServer, PolarityMark, PrivateChannel, Role}
import licos.protocol.village.part.character.{RoleCharacterProtocol, SimpleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.role.SimpleRoleProtocol
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class BoardProtocol(
    village:                    Village,
    character:                  Character,
    role:                       Role,
    prediction:                 PolarityMark,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) {

  val json: Option[JsonBoard] = {
    if (village.isAvailable) {
      Option(
        new JsonBoard(
          BaseProtocol(
            Seq[Context](BaseContext, BoardContext),
            BoardMessage,
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
          ).json(LiCOSOnline.stateVillage(village.id)),
          SimpleRoleProtocol(
            role,
            village.id,
            village.language
          ).json(LiCOSOnline.stateVillage(village.id)),
          prediction.label
        )
      )
    } else {
      None
    }
  }

}
