package licos.protocol.element.village.server2client.server2logger

import licos.entity.Village
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{AnonymousAudienceChannel, Character, Data2Knowledge, Role, ServerToClient, Status}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.TimestampGenerator

import scala.collection.mutable.ListBuffer

final case class AnonymousAudienceChatFromServerProtocol(
    village:                    Village,
    isMine:                     Boolean,
    text:                       String,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

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

      val statusCharacterBuffer = ListBuffer.empty[StatusCharacterProtocol]
      json.base.extensionalDisclosureRange foreach { jsonStatusCharacter: JsonStatusCharacter =>
        val characterOpt: Option[Character] = Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id)
        val roleOpt: Option[Role] = village.cast.parse(jsonStatusCharacter.role.name.en)
        val statusOpt: Option[Status] = Data2Knowledge.statusOpt(jsonStatusCharacter.status)
        if (characterOpt.nonEmpty && roleOpt.nonEmpty && statusOpt.nonEmpty) {
          statusCharacterBuffer += StatusCharacterProtocol(
            characterOpt.get,
            roleOpt.get,
            statusOpt.get,
            jsonStatusCharacter.isHumanPlayer,
            village.id,
            village.language
          )
        }
      }

      Some(AnonymousAudienceChatFromServerProtocol(
        village,
        json.isMine,
        json.text.`@value`,
        statusCharacterBuffer.result
      ))
    } else {
      None
    }
  }

}
