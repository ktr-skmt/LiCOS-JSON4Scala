package licos.protocol.element.village.client2server.server2logger

import java.time.OffsetDateTime

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.client2server.JsonVote
import licos.json.element.village.iri.{Contexts, VoteMessage}
import licos.knowledge.{Character, ClientToServer, Data2Knowledge, PrivateChannel, Role}
import licos.protocol.element.village.client2server.{VoteProtocol => SimpleVoteProtocol}
import licos.protocol.element.village.part.character.{
  RoleCharacterProtocol,
  SimpleCharacterProtocol,
  StatusCharacterProtocol
}
import licos.protocol.element.village.part.{
  BaseProtocol,
  ChatSettingsProtocol,
  VillageProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

final case class VoteProtocol(
    village:                    VillageInfo,
    character:                  Character,
    myCharacter:                Character,
    myRole:                     Role,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  lazy val json: Option[JsonVote] = {
    Some(
      new JsonVote(
        BaseProtocol(
          Contexts.get(VoteMessage),
          VoteMessage,
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
          PrivateChannel,
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
          character,
          village.id,
          village.language
        ).json(LiCOSOnline.stateVillage(village.id))
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def simpleProtocol: SimpleVoteProtocol = SimpleVoteProtocol(
    village:     VillageInfo,
    character:   Character,
    myCharacter: Character,
    myRole:      Role
  )

}

object VoteProtocol {
  def read(json: JsonVote, villageInfoFromLobby: VillageInfoFromLobby): Option[VoteProtocol] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          character   <- Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
          myCharacter <- Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
          myRole      <- village.composition.parse(json.myCharacter.role.name.en)
        } yield {
          VoteProtocol(
            village,
            character,
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
