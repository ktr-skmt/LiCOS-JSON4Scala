package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonVote
import licos.knowledge.{Character, Data2Knowledge, Role}
import play.api.libs.json.{JsValue, Json}

final case class VoteProtocol(village: VillageInfo, character: Character, myCharacter: Character, myRole: Role)
    extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonVote] = {
    server2logger.VoteProtocol(village, character, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonVote =>
      Json.toJson(j)
    }
  }

}

object VoteProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
  def read(json: JsonVote, villageInfoFromLobby: VillageInfoFromLobby): Option[VoteProtocol] = {
    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
        val characterOpt: Option[Character] = Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
        val myCharacterOpt: Option[Character] =
          Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
        val myRoleOpt: Option[Role] = village.cast.parse(json.myCharacter.role.name.en)
        if (characterOpt.nonEmpty && myCharacterOpt.nonEmpty && myRoleOpt.nonEmpty) {
          Some(
            VoteProtocol(
              village,
              characterOpt.get,
              myCharacterOpt.get,
              myRoleOpt.get
            )
          )
        } else {
          None
        }
      case None => None
    }
  }

}
