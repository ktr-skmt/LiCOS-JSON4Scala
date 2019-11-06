package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonPlayedWithToken(to: String, json: JsonPlayed)

object JsonPlayedWithToken {
  implicit val jsonFormat: OFormat[JsonPlayedWithToken] = Json.format[JsonPlayedWithToken]
}
