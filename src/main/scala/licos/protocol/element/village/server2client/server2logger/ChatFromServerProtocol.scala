package licos.protocol.element.village.server2client.server2logger

import licos.entity.Village
import licos.json.element.village.JsonChatFromServer
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{Character, Data2Knowledge, Morning, Role, ServerToClient, Status}
import licos.protocol.PlayerChatChannel
import licos.protocol.element.village.part.character.{SimpleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

final case class ChatFromServerProtocol(
    village:                    Village,
    channel:                    PlayerChatChannel,
    character:                  SimpleCharacterProtocol,
    isMine:                     Boolean,
    id:                         Int,
    counter:                    Int,
    interval:                   Int,
    text:                       String,
    isOver:                     Boolean,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  val json: Option[JsonChatFromServer] = {
    village.currentPhase = Morning
    if (village.isAvailable) {
      Some(
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

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonChatFromServer =>
      Json.toJson(j)
    }
  }
}

object ChatFromServerProtocol {

  def read(json: JsonChatFromServer, village: Village): Option[ChatFromServerProtocol] = {
    val channelOpt: Option[PlayerChatChannel] =
      Data2Knowledge.playerChatChannelOpt(json.base.intensionalDisclosureRange)

    if (channelOpt.nonEmpty && village.myCharacterOpt.nonEmpty && village.myRoleOpt.nonEmpty) {

      val statusCharacterBuffer = ListBuffer.empty[StatusCharacterProtocol]
      json.base.extensionalDisclosureRange foreach { jsonStatusCharacter: JsonStatusCharacter =>
        val characterOpt: Option[Character] =
          Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id)
        val roleOpt:   Option[Role]   = village.cast.parse(jsonStatusCharacter.role.name.en)
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

      Some(
        ChatFromServerProtocol(
          village,
          channelOpt.get,
          SimpleCharacterProtocol(
            village.myCharacterOpt.get,
            village.id,
            village.language
          ),
          json.isMine,
          json.id,
          json.counter,
          json.interval,
          json.text.`@value`,
          json.isOver,
          statusCharacterBuffer.result
        )
      )
    } else {
      None
    }
  }

}
