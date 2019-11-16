package licos.protocol.village.client2server.server2logger

import licos.json.element.village.JsonVote
import licos.json.element.village.iri.{BaseContext, Context, VoteContext, VoteMessage}
import licos.knowledge.{Character, ClientToServer, PrivateChannel, Role}
import licos.protocol.village.part.character.{RoleCharacterProtocol, SimpleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.state.VillageState
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class VoteProtocol(state:                      VillageState,
                              character:                  Character,
                              role:                       Role,
                              extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonVote] = {
    if (state.isAvailable) {
      Option(
        new JsonVote(
          BaseProtocol(
            Seq[Context](BaseContext, VoteContext),
            VoteMessage,
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
          ).json(LiCOSOnline.stateVillage(state.villageId))
        ))
    } else {
      None
    }
  }

}
