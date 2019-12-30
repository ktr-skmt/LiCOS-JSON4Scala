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

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ScrollProtocol {

  def read(json: JsonScroll, villageInfoFromLobby: VillageInfoFromLobby): Option[ScrollProtocol] = {
    VillageInfoFactory
      .create(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          myCharacter <- Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
          myRole      <- village.cast.parse(json.myCharacter.role.name.en)
        } yield {
          ScrollProtocol(
            village,
            json.nodeId,
            json.scrollTop,
            json.scrollHeight,
            json.offsetHeight,
            myCharacter,
            myRole
          )
        }
      }
  }

}
