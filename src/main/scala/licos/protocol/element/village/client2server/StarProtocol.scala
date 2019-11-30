package licos.protocol.element.village.client2server

import java.time.OffsetDateTime

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonStar
import licos.knowledge.{Character, Data2Knowledge, Role}
import play.api.libs.json.{JsValue, Json}

final case class StarProtocol(
    village:         VillageInfo,
    serverTimestamp: OffsetDateTime,
    clientTimestamp: OffsetDateTime,
    isMarked:        Boolean,
    myCharacter:     Character,
    myRole:          Role
) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonStar] = {
    server2logger.StarProtocol(village, serverTimestamp, clientTimestamp, isMarked, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonStar =>
      Json.toJson(j)
    }
  }

}

object StarProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
  def read(json: JsonStar, villageInfoFromLobby: VillageInfoFromLobby): Option[StarProtocol] = {
    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
        val myCharacterOpt: Option[Character] =
          Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
        val myRoleOpt: Option[Role] = village.cast.parse(json.myCharacter.role.name.en)
        if (myCharacterOpt.nonEmpty && myRoleOpt.nonEmpty) {
          Some(
            StarProtocol(
              village,
              OffsetDateTime.parse(json.star.serverTimestamp),
              OffsetDateTime.parse(json.star.clientTimestamp),
              json.star.isMarked,
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
