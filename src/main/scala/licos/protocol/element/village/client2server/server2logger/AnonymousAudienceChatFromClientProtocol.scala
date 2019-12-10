package licos.protocol.element.village.client2server.server2logger

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.knowledge.{AnonymousAudienceChannel, Architecture, Character, ClientToServer, Data2Knowledge, Role, Status}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class AnonymousAudienceChatFromClientProtocol(
    village:                    VillageInfo,
    text:                       String,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonAnonymousAudienceChat] = {
    Some(
      new JsonAnonymousAudienceChat(
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
          AnonymousAudienceChannel,
          extensionalDisclosureRange,
          None,
          None
        ).json,
        isMine = true,
        ChatTextProtocol(text, village.language).json,
        village.maxLengthOfUnicodeCodePoints,
        isFromServer = false
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonAnonymousAudienceChat =>
      Json.toJson(j)
    }
  }

}

object AnonymousAudienceChatFromClientProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.MutableDataStructures",
      "org.wartremover.warts.OptionPartial"
    )
  )
  def read(
      json:                 JsonAnonymousAudienceChat,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[AnonymousAudienceChatFromClientProtocol] = {
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
            AnonymousAudienceChatFromClientProtocol(
              village,
              json.text.`@value`,
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
