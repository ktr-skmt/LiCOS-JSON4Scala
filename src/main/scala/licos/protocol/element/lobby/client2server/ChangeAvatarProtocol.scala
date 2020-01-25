package licos.protocol.element.lobby.client2server

import java.net.URL
import java.util.{Locale, UUID}

import licos.json.element.lobby.client2server.JsonChangeAvatar
import play.api.libs.json.{JsValue, Json}

final case class ChangeAvatarProtocol(token: UUID, name: Option[String], image: Option[URL], language: Option[Locale])
    extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonChangeAvatar] = {
    Some(
      new JsonChangeAvatar(
        token.toString,
        name,
        image.map(_.toString),
        language.map(_.getLanguage)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ChangeAvatarProtocol {
  def read(json: JsonChangeAvatar): Option[ChangeAvatarProtocol] = {
    Some(
      ChangeAvatarProtocol(
        UUID.fromString(json.token),
        json.name,
        json.image.map(new URL(_)),
        json.language.map(new Locale(_))
      )
    )
  }
}
