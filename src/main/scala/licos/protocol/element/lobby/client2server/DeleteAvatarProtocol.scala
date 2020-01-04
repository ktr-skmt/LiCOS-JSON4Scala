package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonDeleteAvatar
import play.api.libs.json.{JsValue, Json}

final case class DeleteAvatarProtocol(token: Seq[UUID]) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonDeleteAvatar] = {
    Some(
      new JsonDeleteAvatar(
        token
          .map(_.toString)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object DeleteAvatarProtocol {

  def read(json: JsonDeleteAvatar): Option[DeleteAvatarProtocol] = {
    Some(
      DeleteAvatarProtocol(
        json.token
          .map(UUID.fromString)
      )
    )
  }

}
