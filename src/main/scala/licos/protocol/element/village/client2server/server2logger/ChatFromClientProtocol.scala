package licos.protocol.element.village.client2server.server2logger

import java.time.OffsetDateTime

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.client2server.JsonChatFromClient
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.knowledge.{Character, ClientToServer, Data2Knowledge, PlayerChatChannel, Role}
import licos.protocol.element.village.client2server.{ChatFromClientProtocol => SimpleChatFromClientProtocol}
import licos.protocol.element.village.part.character.{
  RoleCharacterProtocol,
  SimpleCharacterProtocol,
  StatusCharacterProtocol
}
import licos.protocol.element.village.part.{
  BaseProtocol,
  ChatSettingsProtocol,
  ChatTextProtocol,
  VillageProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

final case class ChatFromClientProtocol(
    village:                    VillageInfo,
    channel:                    PlayerChatChannel,
    text:                       String,
    isOver:                     Boolean,
    myCharacter:                Character,
    myRole:                     Role,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  lazy val json: Option[JsonChatFromClient] = {
    Some(
      new JsonChatFromClient(
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
          Option.empty[OffsetDateTime],
          Some(TimestampGenerator.now),
          ClientToServer,
          channel.channel,
          extensionalDisclosureRange,
          Option.empty[Seq[VotingResultSummaryProtocol]],
          Option.empty[Seq[VotingResultDetailProtocol]]
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
        ).json(LiCOSOnline.stateVillage.concat(s"#${village.id.toString}")),
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

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def simpleProtocol: SimpleChatFromClientProtocol = SimpleChatFromClientProtocol(
    village:     VillageInfo,
    channel:     PlayerChatChannel,
    text:        String,
    isOver:      Boolean,
    myCharacter: Character,
    myRole:      Role
  )
}

object ChatFromClientProtocol {

  def read(json: JsonChatFromClient, villageInfoFromLobby: VillageInfoFromLobby): Option[ChatFromClientProtocol] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          channel     <- Data2Knowledge.playerChatChannelOpt(json.base.intensionalDisclosureRange)
          myCharacter <- Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
          myRole      <- village.composition.parse(json.myCharacter.role.name.en)
        } yield {
          ChatFromClientProtocol(
            village,
            channel,
            json.text.`@value`,
            json.isOver,
            myCharacter,
            myRole,
            json.base.extensionalDisclosureRange.flatMap { jsonStatusCharacter: JsonStatusCharacter =>
              for {
                character  <- Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id).toList
                role       <- village.composition.parse(jsonStatusCharacter.role.name.en).toList
                status     <- Data2Knowledge.statusOpt(jsonStatusCharacter.status).toList
                playerType <- Data2Knowledge.architectureOpt(jsonStatusCharacter.playerType).toList
              } yield {
                StatusCharacterProtocol(
                  character,
                  role,
                  status,
                  playerType,
                  village.id,
                  village.language
                )
              }
            }
          )
        }
      }
  }

}
