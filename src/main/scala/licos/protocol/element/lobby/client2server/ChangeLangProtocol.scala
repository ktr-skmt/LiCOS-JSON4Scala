package licos.protocol.element.lobby.client2server

import java.util.Locale

import licos.json.element.lobby.JsonChangeLang
import play.api.libs.json.{JsValue, Json}

final case class ChangeLangProtocol(lang: Locale) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonChangeLang] = {
    Some(
      new JsonChangeLang(
        lang.getLanguage
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object ChangeLangProtocol {

  def read(json: JsonChangeLang): Option[ChangeLangProtocol] = {
    Some(ChangeLangProtocol(new Locale(json.lang)))
  }

}
