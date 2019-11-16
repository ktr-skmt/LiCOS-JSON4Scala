package licos.protocol.village.client2server.server2logger

import licos.json.element.village.JsonBoard
import licos.json.element.village.iri.{BaseContext, BoardContext, BoardMessage, Context}
import licos.knowledge.{Character, ClientToServer, PolarityMark, PrivateChannel, Role}
import licos.protocol.village.part.character.{RoleCharacterProtocol, SimpleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.role.SimpleRoleProtocol
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.state.VillageState
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class BoardProtocol(state:                      VillageState,
                               character:                  Character,
                               role:                       Role,
                               prediction:                 PolarityMark,
                               extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonBoard] = {
    if (state.isAvailable) {
      Option(
        new JsonBoard(
          BaseProtocol(
            Seq[Context](BaseContext, BoardContext),
            BoardMessage,
            VillageProtocol(
              state.villageId,
              state.villageName,
              state.totalNumberOfCharacters,
              state.lang,
              ChatSettingsProtocol(
                state.villageId,
                state.maxNumberOfChatMessages,
                state.maxLengthOfUnicodeCodePoints
              )
            ),
            state.token,
            state.phase.get,
            state.day.get,
            state.phaseTimeLimit.get,
            state.phaseStartTime.get,
            None,
            Option(TimestampGenerator.now),
            ClientToServer,
            PrivateChannel,
            extensionalDisclosureRange,
            Nil,
            Nil
          ).json,
          RoleCharacterProtocol(
            state.myCharacter,
            state.villageId,
            state.lang,
            state.myRole
          ).json,
          SimpleCharacterProtocol(
            character,
            state.villageId,
            state.lang,
            role
          ).json(LiCOSOnline.stateVillage(state.villageId)),
          SimpleRoleProtocol(
            role,
            state.villageId,
            state.lang
          ).json(LiCOSOnline.stateVillage(state.villageId)),
          prediction.label
        ))
    } else {
      None
    }
  }

}
