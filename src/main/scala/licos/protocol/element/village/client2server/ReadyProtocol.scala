package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.lobby.JsonReady
import play.api.libs.json.{JsValue, Json}

final case class ReadyProtocol(village: Village) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonReady] = {
    if (village.isAvailable) {
      Some(
        new JsonReady(
          village.tokenOpt.get.toString,
          village.id
        )
      )
    } else {
      None
    }
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object ReadyProtocol {

  def read(json: JsonReady, village: Village): Option[ReadyProtocol] = {
    Some(ReadyProtocol(village))
  }

}