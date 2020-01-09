package licos.protocol.element.village.client2server

import java.net.URL

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonOnymousAudienceBoard
import licos.knowledge.{Character, Data2Knowledge, PolarityMark, Role}
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceBoardProtocol(
    village:       VillageInfo,
    character:     Character,
    role:          Role,
    prediction:    PolarityMark,
    myAvatarName:  String,
    myAvatarImage: URL
) extends Client2ServerVillageMessageProtocol {

  private lazy val json: Option[JsonOnymousAudienceBoard] = {
    server2logger
      .OnymousAudienceBoardProtocol(village, character, role, prediction, myAvatarName, myAvatarImage, Nil)
      .json
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
            new URL(json.avatar.image)
          )
        }
      }
  }

}
