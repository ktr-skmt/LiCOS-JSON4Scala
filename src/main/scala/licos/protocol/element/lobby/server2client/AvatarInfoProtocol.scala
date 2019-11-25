package licos.protocol.element.lobby.server2client

import java.net.URL
import java.util.{Locale, UUID}

import licos.json.element.lobby.JsonAvatarInfo
import play.api.libs.json.{JsValue, Json}

final case class AvatarInfoProtocol(token: UUID, name: String, image: URL, lang: Locale)
    extends Server2ClientLobbyMessageProtocol {

  private val json: Option[JsonAvatarInfo] = {
    Some(
      new JsonAvatarInfo(
        token.toString,
        name,
        image.toString,
        lang.getLanguage
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonAvatarInfo =>
      Json.toJson(j)
    }
  }

}

object AvatarInfoProtocol {

  def read(json: JsonAvatarInfo): Option[AvatarInfoProtocol] = {
    Some(
      AvatarInfoProtocol(
        UUID.fromString(json.token),
        json.name,
        new URL(json.image),
        new Locale(json.lang)
      )
    )
  }

}
