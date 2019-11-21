package licos.protocol.element.village.server2client.server2logger

import licos.entity.Village
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{AnonymousAudienceChannel, ServerToClient}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.TimestampGenerator

final case class AnonymousAudienceChatFromServerProtocol(
    village:                    Village,
    isMine:                     Boolean,
    text:                       String,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends VillageMessageProtocol {

  val json: Option[JsonAnonymousAudienceChat] = {
    if (village.isAvailable) {
      Some(
        new JsonAnonymousAudienceChat(
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
            AnonymousAudienceChannel,
            extensionalDisclosureRange,
            Nil,
            Nil
          ).json,
          isMine,
          ChatTextProtocol(
            text,
            village.language
          ).json,
          village.maxLengthOfUnicodeCodePoints,
          isFromServer = true
        )
      )
    } else {
      None
    }
  }

}

object AnonymousAudienceChatFromServerProtocol {

  def read(json: JsonAnonymousAudienceChat, village: Village): Option[AnonymousAudienceChatFromServerProtocol] = {

    if (json.isFromServer) {
      Option(AnonymousAudienceChatFromServerProtocol(
        village,
        json.isMine,
        json.text.`@value`,
        //Nil
      ))
    } else {
      None
    }

  }

}
