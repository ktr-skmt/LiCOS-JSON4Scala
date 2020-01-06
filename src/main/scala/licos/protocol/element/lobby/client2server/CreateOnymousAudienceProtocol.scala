package licos.protocol.element.lobby.client2server

import java.net.URL
import java.util.Locale

import licos.json.element.lobby.client2server.JsonCreateOnymousAudience
import play.api.libs.json.{JsValue, Json}

final case class CreateOnymousAudienceProtocol(name: String, image: URL, lang: Locale)
    extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonCreateOnymousAudience] = {
    Some(
      new JsonCreateOnymousAudience(
        name,
        image.toString,
        lang.getLanguage
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object CreateOnymousAudienceProtocol {

  def read(json: JsonCreateOnymousAudience): Option[CreateOnymousAudienceProtocol] = {
    Some(
      CreateOnymousAudienceProtocol(
        json.name,
        new URL(json.image),
        new Locale(json.lang)
      )
    )
  }

}
