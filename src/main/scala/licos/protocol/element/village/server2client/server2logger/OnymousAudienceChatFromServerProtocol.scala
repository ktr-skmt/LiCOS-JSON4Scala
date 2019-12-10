package licos.protocol.element.village.server2client.server2logger

import java.net.URL

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonOnymousAudienceChat
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.knowledge.{Architecture, Character, Data2Knowledge, OnymousAudienceChannel, Role, ServerToClient, Status}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{
  AvatarProtocol,
  BaseProtocol,
  ChatSettingsProtocol,
  ChatTextProtocol,
  VillageProtocol
}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class OnymousAudienceChatFromServerProtocol(
    village:                    VillageInfo,
    isMine:                     Boolean,
    text:                       String,
    myAvatarName:               String,
    myAvatarImage:              URL,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  val json: Option[JsonOnymousAudienceChat] = {
    Some(
      new JsonOnymousAudienceChat(
        BaseProtocol(
          Contexts.get(ChatMessage),
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
          village.token,
          village.phase,
          village.day,
          village.phaseTimeLimit,
          village.phaseStartTime,
          Option(TimestampGenerator.now),
          None,
          ServerToClient,
          OnymousAudienceChannel,
          extensionalDisclosureRange,
          None,
          None
        ).json,
        AvatarProtocol(
          village.token,
          myAvatarName,
          myAvatarImage
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
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonOnymousAudienceChat =>
      Json.toJson(j)
    }
  }

}

object OnymousAudienceChatFromServerProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.OptionPartial",
      "org.wartremover.warts.MutableDataStructures"
    )
  )
  def read(
      json:                 JsonOnymousAudienceChat,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[OnymousAudienceChatFromServerProtocol] = {
    if (json.isFromServer) {
      VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
        case Some(village: VillageInfo) =>
          val statusCharacterBuffer = ListBuffer.empty[StatusCharacterProtocol]
          json.base.extensionalDisclosureRange foreach { jsonStatusCharacter: JsonStatusCharacter =>
            val characterOpt: Option[Character] =
              Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id)
            val roleOpt:       Option[Role]         = village.cast.parse(jsonStatusCharacter.role.name.en)
            val statusOpt:     Option[Status]       = Data2Knowledge.statusOpt(jsonStatusCharacter.status)
            val playerTypeOpt: Option[Architecture] = Data2Knowledge.architectureOpt(jsonStatusCharacter.playerType)
            if (characterOpt.nonEmpty && roleOpt.nonEmpty && statusOpt.nonEmpty && playerTypeOpt.nonEmpty) {
              statusCharacterBuffer += StatusCharacterProtocol(
                characterOpt.get,
                roleOpt.get,
                statusOpt.get,
                playerTypeOpt.get,
                village.id,
                village.language
              )
            }
          }

          Some(
            OnymousAudienceChatFromServerProtocol(
              village,
              json.isMine,
              json.text.`@value`,
              json.avatar.name,
              new URL(json.avatar.image),
              statusCharacterBuffer.result
            )
          )
        case None => None
      }
    } else {
      None
    }
  }

}
