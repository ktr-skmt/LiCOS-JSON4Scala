package licos.protocol.element.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceChat
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{ClientToServer, OnymousAudienceChannel}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{AvatarProtocol, BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class OnymousAudienceChatFromClientProtocol(
    village:                    Village,
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
            None,
            Option(TimestampGenerator.now),
            ClientToServer,
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
          isMine = true,
          ChatTextProtocol(
            text,
            village.language
          ).json,
          village.maxLengthOfUnicodeCodePoints,
          isFromServer = false
        )
      )
    } else {
      None
    }
  }

}

object OnymousAudienceChatFromClientProtocol {

  def read(json: JsonOnymousAudienceChat): Option[OnymousAudienceChatFromClientProtocol] = {

  }

}
