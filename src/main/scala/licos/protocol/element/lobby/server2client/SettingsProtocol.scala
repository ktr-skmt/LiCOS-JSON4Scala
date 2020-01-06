package licos.protocol.element.lobby.server2client

import java.util.Locale

import licos.json.element.lobby.server2client.JsonSettings
import play.api.libs.json.{JsValue, Json}

final case class SettingsProtocol(userName: String, userEmail: String, lang: Locale)
    extends Server2ClientLobbyMessageProtocol {

  private lazy val json: Option[JsonSettings] = {
    Some(
      new JsonSettings(
        userName,
        userEmail,
        lang.getLanguage
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object SettingsProtocol {

  def read(json: JsonSettings): Option[SettingsProtocol] = {
    Some(
      SettingsProtocol(
        json.userName,
        json.userEmail,
        new Locale(json.lang)
      )
    )
  }

}
