package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonSelectVillage
import play.api.libs.json.{JsValue, Json}

final case class SelectVillageProtocol(token: UUID, villageId: Long) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonSelectVillage] = {
    Some(
      new JsonSelectVillage(
        token.toString,
        villageId
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object SelectVillageProtocol {

  def read(json: JsonSelectVillage): Option[SelectVillageProtocol] = {
    Some(
      SelectVillageProtocol(
        UUID.fromString(json.token),
        json.villageId
      )
    )
  }

}
