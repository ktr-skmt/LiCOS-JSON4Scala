package licos.protocol.element.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonChatFromClient
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.ClientToServer
import licos.protocol.PlayerChatChannel
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.character.{RoleCharacterProtocol, SimpleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class ChatFromClientProtocol(
    village:                    Village,
    channel:                    PlayerChatChannel,
    text:                       String,
    isOver:                     Boolean,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends VillageMessageProtocol {

  val json: Option[JsonChatFromClient] = {
    if (village.isAvailable) {
      Some(
        new JsonChatFromClient(
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
            channel.channel,
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
            village.myCharacterOpt.get,
            village.id,
            village.language,
            village.myRoleOpt.get
          ).json(LiCOSOnline.stateVillage.concat(s"#${village.id}")),
          isMine = true,
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

object CharFromClientProtocol {

  def read(json: JsonChatFromClient): Option[ChatFromClientProtocol] = {

  }

}
