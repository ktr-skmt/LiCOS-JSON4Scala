package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonBoard
import licos.knowledge.{Character, Data2Knowledge, PolarityMark, Role}
import play.api.libs.json.{JsValue, Json}

final case class BoardProtocol(
    village:     VillageInfo,
    character:   Character,
    role:        Role,
    prediction:  PolarityMark,
    myCharacter: Character,
    myRole:      Role
) extends Client2ServerVillageMessageProtocol {

  private lazy val json: Option[JsonBoard] = {
    server2logger.BoardProtocol(village, character, role, prediction, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object BoardProtocol {

  def read(json: JsonBoard, villageInfoFromLobby: VillageInfoFromLobby): Option[BoardProtocol] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          prediction <- Data2Knowledge.polarityMarkOpt(json.prediction)
          character  <- Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
          role <- Data2Knowledge
            .roleOpt(
              json.role.name.en,
              village.composition.parse(json.role.name.en).map(_.numberOfPlayers).getOrElse(0)
            )
          myCharacter <- Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
          myRole      <- village.composition.parse(json.myCharacter.role.name.en)
        } yield {
          BoardProtocol(
            village,
            character,
            role,
            prediction,
            myCharacter,
            myRole
          )
        }
      }
  }

}
