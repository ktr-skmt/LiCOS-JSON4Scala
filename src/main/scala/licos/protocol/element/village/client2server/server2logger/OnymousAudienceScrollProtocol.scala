package licos.protocol.element.village.client2server.server2logger

import java.net.URL
import java.time.OffsetDateTime

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.client2server.JsonOnymousAudienceScroll
import licos.json.element.village.iri.{Contexts, ScrollMessage}
import licos.knowledge.{ClientToServer, Data2Knowledge, PrivateChannel}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{
  AvatarProtocol,
  BaseProtocol,
  ChatSettingsProtocol,
  VillageProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.protocol.element.village.client2server.{
  OnymousAudienceScrollProtocol => SimpleOnymousAudienceScrollProtocol
}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceScrollProtocol(
    village:                    VillageInfo,
    nodeId:                     String,
    scrollTop:                  Int,
    scrollHeight:               Int,
    offsetHeight:               Int,
    myAvatarName:               String,
    myAvatarImage:              URL,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  lazy val json: Option[JsonOnymousAudienceScroll] = {
    Some(
      new JsonOnymousAudienceScroll(
        BaseProtocol(
          Contexts.get(ScrollMessage),
          ScrollMessage,
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
        AvatarProtocol(
          village.token,
          myAvatarName,
          myAvatarImage
        ).json(LiCOSOnline.stateVillage(village.id)),
        nodeId,
        scrollTop,
        scrollHeight,
        offsetHeight
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def simpleProtocol: SimpleOnymousAudienceScrollProtocol = SimpleOnymousAudienceScrollProtocol(
    village:       VillageInfo,
    nodeId:        String,
    scrollTop:     Int,
    scrollHeight:  Int,
    offsetHeight:  Int,
    myAvatarName:  String,
    myAvatarImage: URL
  )

}

object OnymousAudienceScrollProtocol {

  def read(
      json:                 JsonOnymousAudienceScroll,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[OnymousAudienceScrollProtocol] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .map { village: VillageInfo =>
        OnymousAudienceScrollProtocol(
          village,
          json.nodeId,
          json.scrollTop,
          json.scrollHeight,
          json.offsetHeight,
          json.avatar.name,
          new URL(json.avatar.image),
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
