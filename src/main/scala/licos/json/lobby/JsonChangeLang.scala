package licos.json.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonChangeLang(`type`: String,
                          lang: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonChangeLang.`type`
}

object JsonChangeLang {
  implicit val jsonFormat: OFormat[JsonChangeLang] = Json.format[JsonChangeLang]

  val `type`: String = "changeLang"
}
