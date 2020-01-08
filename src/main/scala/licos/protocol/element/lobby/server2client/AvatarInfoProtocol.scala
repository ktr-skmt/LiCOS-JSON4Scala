package licos.protocol.element.lobby.server2client

import java.net.URL
import java.util.{Locale, UUID}

import licos.json.element.lobby.server2client.{JsonAvatarInfo, JsonSubAvatarInfo}
import play.api.libs.json.{JsValue, Json}

final case class AvatarInfoProtocol(token: UUID, name: String, image: URL, language: Locale)
    extends Server2ClientLobbyMessageProtocol {

  private lazy val json: Option[JsonAvatarInfo] = {
    Some(
      new JsonAvatarInfo(
        token.toString,
        name,
        image.toString,
        language.getLanguage
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def jsonWithoutType: Option[JsonSubAvatarInfo] = {
    Some(
      new JsonSubAvatarInfo(
        token.toString,
        name,
        image.toString,
        language.getLanguage
      )
    )
  }

}

object AvatarInfoProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def read(json: JsonAvatarInfo): Option[AvatarInfoProtocol] = {
    Some(
      AvatarInfoProtocol(
        UUID.fromString(json.token),
        json.name,
        new URL(json.image),
        new Locale(json.language)
      )
    )
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def read(json: JsonSubAvatarInfo): Option[AvatarInfoProtocol] = {
    Some(
      AvatarInfoProtocol(
        UUID.fromString(json.token),
        json.name,
        new URL(json.image),
        new Locale(json.language)
      )
    )
  }

}
