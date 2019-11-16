package licos.protocol.village.client2server.server2logger

import licos.json.element.village.JsonScroll
import licos.json.element.village.iri.{BaseContext, Context, ScrollContext, ScrollMessage}
import licos.knowledge.{ClientToServer, PrivateChannel}
import licos.protocol.village.part.character.{RoleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.state.VillageState
import licos.util.TimestampGenerator

final case class ScrollProtocol(state:                      VillageState,
                                nodeId:                     String,
                                scrollTop:                  Int,
                                scrollHeight:               Int,
                                offsetHeight:               Int,
                                extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonScroll] = {
    if (state.isAvailable) {
      Option(
        new JsonScroll(
          BaseProtocol(
            Seq[Context](BaseContext, ScrollContext),
            ScrollMessage,
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
          nodeId,
          scrollTop,
          scrollHeight,
          offsetHeight
        ))
    } else {
      None
    }
  }

}
