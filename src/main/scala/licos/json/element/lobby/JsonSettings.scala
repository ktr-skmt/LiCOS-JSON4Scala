package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonSettings(`type`: String, userName: String, userEmail: String, lang: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonSettings.`type`
}

object JsonSettings {
  implicit val jsonFormat: OFormat[JsonSettings] = Json.format[JsonSettings]

  val `type`: String = "settings"

  def generate(userName: String, userEmail: String, lang: String): JsonSettings = {
    JsonSettings(`type`, userName, userEmail, lang)
  }
}
