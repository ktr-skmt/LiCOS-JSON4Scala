package licos.protocol.village.server2client.server2logger

import licos.entity.Village
import licos.json.element.village.JsonChatFromServer
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{Morning, ServerToClient}
import licos.protocol.PlayerChatChannel
import licos.protocol.village.part.character.{SimpleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class ChatFromServerProtocol(
    village:                    Village,
    channel:                    PlayerChatChannel,
    character:                  SimpleCharacterProtocol,
    isMine:                     Boolean,
    id:                         Int,
    counter:                    Int,
    interval:                   String,
    text:                       String,
    isOver:                     Boolean,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) {

  val json: Option[JsonChatFromServer] = {
    village.currentPhase = Morning
    if (village.isAvailable) {
      Option(
        new JsonChatFromServer(
          BaseProtocol(
            Seq[Context](BaseContext, ChatContext),
            ChatMessage,
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
            channel.channel,
            extensionalDisclosureRange,
            Nil,
            Nil
          ).json,
          character.json(LiCOSOnline.stateVillage(village.id)),
          isMine,
          id,
          counter,
          village.maxNumberOfChatMessages,
          interval,
          ChatTextProtocol(
            text,
            village.language
          ).json,
          village.maxLengthOfUnicodeCodePoints,
          isOver
        )
      )
    } else {
      None
    }
  }

}
