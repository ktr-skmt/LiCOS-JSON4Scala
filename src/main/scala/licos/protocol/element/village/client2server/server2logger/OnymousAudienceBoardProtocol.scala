package licos.protocol.element.village.client2server.server2logger

import java.net.URL

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.client2server.JsonOnymousAudienceBoard
import licos.json.element.village.iri.{BoardMessage, Contexts}
import licos.knowledge.{Character, ClientToServer, Data2Knowledge, OnymousAudienceChannel, PolarityMark, Role}
import licos.protocol.element.village.part.character.{SimpleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.element.village.part.role.SimpleRoleProtocol
import licos.protocol.element.village.part.{AvatarProtocol, BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceBoardProtocol(
    village:                    VillageInfo,
    character:                  Character,
    role:                       Role,
    prediction:                 PolarityMark,
    myAvatarName:               String,
    myAvatarImage:              URL,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  lazy val json: Option[JsonOnymousAudienceBoard] = {
    Some(
      new JsonOnymousAudienceBoard(
        BaseProtocol(
          Contexts.get(BoardMessage),
          BoardMessage,
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
          None,
          Some(TimestampGenerator.now),
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
        SimpleCharacterProtocol(
          character,
          village.id,
          village.language
        ).json(LiCOSOnline.stateVillage(village.id)),
        SimpleRoleProtocol(
          role,
          village.id,
          village.language
        ).json(LiCOSOnline.stateVillage(village.id)),
        prediction.label
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object OnymousAudienceBoardProtocol {

  def read(
      json:                 JsonOnymousAudienceBoard,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[OnymousAudienceBoardProtocol] = {
    VillageInfoFactory
      .create(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          prediction <- Data2Knowledge.polarityMarkOpt(json.prediction)
          character  <- Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
          role <- Data2Knowledge
            .roleOpt(
              json.role.name.en,
              village.composition.parse(json.role.name.en).map(_.numberOfPlayers).getOrElse(0)
            )
        } yield {
          OnymousAudienceBoardProtocol(
            village,
            character,
            role,
            prediction,
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

}
