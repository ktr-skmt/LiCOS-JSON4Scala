package licos.json.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonGetSettings(`type`: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonGetSettings.`type`
}

object JsonGetSettings {
  implicit val jsonFormat: OFormat[JsonGetSettings] = Json.format[JsonGetSettings]

  val `type`: String = "getSettings"
}
