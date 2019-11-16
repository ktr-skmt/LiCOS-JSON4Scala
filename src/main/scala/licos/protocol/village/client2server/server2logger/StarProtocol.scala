package licos.protocol.village.client2server.server2logger

import java.time.OffsetDateTime

import licos.json.element.village.JsonStar
import licos.json.element.village.iri.{BaseContext, Context, StarContext, StarMessage}
import licos.knowledge.{Character, ClientToServer, PrivateChannel, Role}
import licos.protocol.village.part.character.{RoleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, StarInfoProtocol, VillageProtocol}
import licos.state.VillageState
import licos.util.TimestampGenerator

final case class StarProtocol(state:                      VillageState,
                              serverTimestamp:            OffsetDateTime,
                              clientTimestamp:            OffsetDateTime,
                              isMarked:                   Boolean,
                              extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonStar] = {
    if (state.isAvailable) {
      Option(
        new JsonStar(
          BaseProtocol(
            Seq[Context](BaseContext, StarContext),
            StarMessage,
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
          StarInfoProtocol(
            state.villageId,
            state.token,
            serverTimestamp,
            clientTimestamp,
            isMarked
          ).json
        ))
    } else {
      None
    }
  }

}
