package licos.json.element.lobby

import licos.json.element.Element
import play.api.libs.json.{Json, OFormat}

final case class JsonPlayedWithToken(to: String, json: JsonPlayed) extends Element

object JsonPlayedWithToken {
  implicit val jsonFormat: OFormat[JsonPlayedWithToken] = Json.format[JsonPlayedWithToken]
}
