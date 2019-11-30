package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonScroll
import licos.knowledge.{Character, Data2Knowledge, Role}
import play.api.libs.json.{JsValue, Json}

final case class ScrollProtocol(
    village:      VillageInfo,
    nodeId:       String,
    scrollTop:    Int,
    scrollHeight: Int,
    offsetHeight: Int,
    myCharacter:  Character,
    myRole:       Role
) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonScroll] = {
    server2logger.ScrollProtocol(village, nodeId, scrollTop, scrollHeight, offsetHeight, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonScroll =>
      Json.toJson(j)
    }
  }

}

object ScrollProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
  def read(json: JsonScroll, villageInfoFromLobby: VillageInfoFromLobby): Option[ScrollProtocol] = {
    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
        val myCharacterOpt: Option[Character] =
          Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
        val myRoleOpt: Option[Role] = village.cast.parse(json.myCharacter.role.name.en)
        if (myCharacterOpt.nonEmpty && myRoleOpt.nonEmpty) {
          Some(
            ScrollProtocol(
              village,
              json.nodeId,
              json.scrollTop,
              json.scrollHeight,
              json.offsetHeight,
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
