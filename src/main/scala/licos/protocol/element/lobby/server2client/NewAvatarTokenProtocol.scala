package licos.protocol.element.lobby.server2client

import java.util.UUID

import licos.json.element.lobby.server2client.JsonNewAvatarToken
import play.api.libs.json.{JsValue, Json}

final case class NewAvatarTokenProtocol(token: UUID) extends Server2ClientLobbyMessageProtocol {

  private val json: Option[JsonNewAvatarToken] = Some(new JsonNewAvatarToken(token.toString))

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object NewAvatarTokenProtocol {

  def read(jsonNewAvatarToken: JsonNewAvatarToken): Option[NewAvatarTokenProtocol] = {
    Some(
      NewAvatarTokenProtocol(
        UUID.fromString(jsonNewAvatarToken.token)
      )
    )
  }

}