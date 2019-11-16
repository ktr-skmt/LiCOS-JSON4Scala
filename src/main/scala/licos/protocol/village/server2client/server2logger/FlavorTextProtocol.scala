package licos.protocol.village.server2client.server2logger

import licos.json.element.village.JsonFlavorText
import licos.json.element.village.iri.{BaseContext, Context, FlavorTextContext, FlavorTextMessage}
import licos.knowledge.{FlavorText, PublicChannel, ServerToClient}
import licos.protocol.village.part.character.StatusCharacterProtocol
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.state.VillageState
import licos.util.TimestampGenerator

final case class FlavorTextProtocol(state:                      VillageState,
                                    flavorText:                 Seq[licos.protocol.village.server2client.ChatFromServerProtocol],
                                    extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonFlavorText] = {
    state.phase = Some(FlavorText)
    if (state.isAvailable) {
      Option(
        new JsonFlavorText(
          BaseProtocol(
            Seq[Context](BaseContext, FlavorTextContext),
            FlavorTextMessage,
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
            Option(TimestampGenerator.now),
            None,
            ServerToClient,
            PublicChannel,
            Nil,
            Nil,
            Nil
          ).json,
          flavorText.flatMap(_.json)
        ))
    } else {
      None
    }
  }

}
