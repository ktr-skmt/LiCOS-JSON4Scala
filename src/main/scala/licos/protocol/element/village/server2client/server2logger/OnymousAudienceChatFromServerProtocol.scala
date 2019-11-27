package licos.protocol.element.village.server2client.server2logger

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceChat
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.knowledge.{Character, Data2Knowledge, OnymousAudienceChannel, Role, ServerToClient, Status}
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
    village:                    Village,
    isMine:                     Boolean,
    text:                       String,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  val json: Option[JsonOnymousAudienceChat] = {
    if (village.isAvailable) {
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
            None,
            None
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
  def read(json: JsonOnymousAudienceChat, village: Village): Option[OnymousAudienceChatFromServerProtocol] = {
    if (json.isFromServer) {

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
        OnymousAudienceChatFromServerProtocol(
          village,
          json.isMine,
          json.text.`@value`,
          statusCharacterBuffer.result
        )
      )
    } else {
      None
    }
  }

}
