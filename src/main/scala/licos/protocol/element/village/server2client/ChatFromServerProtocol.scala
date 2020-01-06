package licos.protocol.element.village.server2client

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.json.element.village.server2client.JsonChatFromServer
import licos.knowledge.{Data2Knowledge, ServerToClient}
import licos.protocol.PlayerChatChannel
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.protocol.element.village.part.character.SimpleCharacterProtocol
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

final case class ChatFromServerProtocol(
    village:   VillageInfo,
    channel:   PlayerChatChannel,
    character: SimpleCharacterProtocol,
    isMine:    Boolean,
    id:        Int,
    counter:   Int,
    interval:  Int,
    text:      String,
    isOver:    Boolean
) extends Server2ClientVillageMessageProtocol {

  lazy val json: Option[JsonChatFromServer] = {
    Some(
      new JsonChatFromServer(
        BaseProtocol(
          Contexts.get(ChatMessage),
          ChatMessage,
          VillageProtocol(
            village.id,
            village.name,
            village.composition.totalNumberOfPlayers,
            village.language,
            ChatSettingsProtocol(
              village.id,
              village.maxNumberOfChatMessages,
              village.maxLengthOfUnicodeCodePoints
            )
          ),
          village.token,
          village.phase,
          village.day,
          village.phaseTimeLimit,
          village.phaseStartTime,
          Some(TimestampGenerator.now),
          None,
          ServerToClient,
          channel.channel,
          Nil,
          None,
          None
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
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ChatFromServerProtocol {

  def read(json: JsonChatFromServer, villageInfoFromLobby: VillageInfoFromLobby): Option[ChatFromServerProtocol] = {
    VillageInfoFactory
      .create(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          channel   <- Data2Knowledge.playerChatChannelOpt(json.base.intensionalDisclosureRange)
          character <- Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
        } yield {
          ChatFromServerProtocol(
            village,
            channel,
            SimpleCharacterProtocol(
              character,
              village.id,
              village.language
            ),
            json.isMine,
            json.id,
            json.counter,
            json.interval,
            json.text.`@value`,
            json.isOver
          )
        }
      }
  }

}
