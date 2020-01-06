package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonRenewAvatarToken
import play.api.libs.json.{JsValue, Json}

final case class RenewAvatarTokenProtocol(token: UUID) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonRenewAvatarToken] = Some(new JsonRenewAvatarToken(token.toString))

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object RenewAvatarTokenProtocol {

  def read(jsonRenewAvatarToken: JsonRenewAvatarToken): Option[RenewAvatarTokenProtocol] = {
    Some(
      RenewAvatarTokenProtocol(
        UUID.fromString(jsonRenewAvatarToken.token)
      )
    )
  }

}
