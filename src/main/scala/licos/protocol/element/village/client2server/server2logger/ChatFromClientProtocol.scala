package licos.protocol.element.village.client2server.server2logger

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.client2server.JsonChatFromClient
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.knowledge.{Character, ClientToServer, Data2Knowledge, Role, Status}
import licos.protocol.PlayerChatChannel
import licos.protocol.element.village.part.character.{
  RoleCharacterProtocol,
  SimpleCharacterProtocol,
  StatusCharacterProtocol
}
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class ChatFromClientProtocol(
    village:                    VillageInfo,
    channel:                    PlayerChatChannel,
    text:                       String,
    isOver:                     Boolean,
    myCharacter:                Character,
    myRole:                     Role,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonChatFromClient] = {
    Some(
      new JsonChatFromClient(
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
          channel.channel,
          extensionalDisclosureRange,
          None,
          None
        ).json,
        RoleCharacterProtocol(
          myCharacter,
          myRole,
          village.id,
          village.language
        ).json,
        SimpleCharacterProtocol(
          myCharacter,
          village.id,
          village.language
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
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonChatFromClient =>
      Json.toJson(j)
    }
  }

}

object ChatFromClientProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.MutableDataStructures",
      "org.wartremover.warts.OptionPartial"
    )
  )
  def read(json: JsonChatFromClient, villageInfoFromLobby: VillageInfoFromLobby): Option[ChatFromClientProtocol] = {

    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
        val channelOpt: Option[PlayerChatChannel] =
          Data2Knowledge.playerChatChannelOpt(json.base.intensionalDisclosureRange)
        val myCharacterOpt: Option[Character] =
          Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
        val myRoleOpt: Option[Role] = village.cast.parse(json.myCharacter.role.name.en)

        if (channelOpt.nonEmpty && myCharacterOpt.nonEmpty && myRoleOpt.nonEmpty) {

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
            ChatFromClientProtocol(
              village,
              channelOpt.get,
              json.text.`@value`,
              json.isOver,
              myCharacterOpt.get,
              myRoleOpt.get,
              statusCharacterBuffer.result
            )
          )
        } else {
          None
        }
      case None => None
    }
  }

}
