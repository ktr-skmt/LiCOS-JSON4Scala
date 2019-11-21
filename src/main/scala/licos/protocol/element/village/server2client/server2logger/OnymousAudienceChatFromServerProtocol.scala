package licos.protocol.element.village.server2client.server2logger

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceChat
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{OnymousAudienceChannel, ServerToClient}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{AvatarProtocol, BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class OnymousAudienceChatFromServerProtocol(
    village:                    Village,
    isMine:                     Boolean,
    text:                       String,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends VillageMessageProtocol {

  val json: Option[JsonOnymousAudienceChat] = {
    if (village.isAvailable) {
      Some(
        new JsonOnymousAudienceChat(
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
            OnymousAudienceChannel,
            extensionalDisclosureRange,
            Nil,
            Nil
          ).json,
          AvatarProtocol(
            village.tokenOpt.get,
            village.avatarNameOpt.get,
            village.avatarImageOpt.get
          ).json(LiCOSOnline.stateVillage(village.id)),
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

object OnymousAudienceChatFromServerProtocol {

  def read(json: JsonOnymousAudienceChat): Option[OnymousAudienceChatFromServerProtocol] = {

  }

}
