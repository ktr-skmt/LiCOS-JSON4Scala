package licos.protocol.element.village.client2server.server2logger

import java.net.URL

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonOnymousAudienceChat
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.knowledge.{Architecture, Character, ClientToServer, Data2Knowledge, OnymousAudienceChannel, Role, Status}
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
final case class OnymousAudienceChatFromClientProtocol(
    village:                    VillageInfo,
    text:                       String,
    myAvatarName:               String,
    myAvatarImage:              URL,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

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
          None,
          Option(TimestampGenerator.now),
          ClientToServer,
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
        isMine = true,
        ChatTextProtocol(
          text,
          village.language
        ).json,
        village.maxLengthOfUnicodeCodePoints,
        isFromServer = false
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonOnymousAudienceChat =>
      Json.toJson(j)
    }
  }

}

object OnymousAudienceChatFromClientProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.MutableDataStructures",
      "org.wartremover.warts.OptionPartial"
    )
  )
  def read(
      json:                 JsonOnymousAudienceChat,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[OnymousAudienceChatFromClientProtocol] = {
    if (!json.isFromServer) {
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
            OnymousAudienceChatFromClientProtocol(
              village,
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
