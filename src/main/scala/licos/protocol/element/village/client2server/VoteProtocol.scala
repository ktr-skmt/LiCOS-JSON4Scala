package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonVote
import licos.knowledge.{Character, Data2Knowledge, Role}
import play.api.libs.json.{JsValue, Json}

final case class VoteProtocol(village: VillageInfo, character: Character, myCharacter: Character, myRole: Role)
    extends Client2ServerVillageMessageProtocol {

  private lazy val json: Option[JsonVote] = {
    server2logger.VoteProtocol(village, character, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
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
            myRole
          )
        }
      }
  }

}
