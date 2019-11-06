package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonAvatarInfo(`type`: String, token: String, name: String, image: String, lang: String)
    extends TypeSystem(`type`) {
  override protected def validType: String = JsonAvatarInfo.`type`
}

object JsonAvatarInfo {
  implicit val jsonFormat: OFormat[JsonAvatarInfo] = Json.format[JsonAvatarInfo]

  val `type`: String = "avatar"

  def generate(token: String, name: String, image: String, lang: String): JsonAvatarInfo = {
    JsonAvatarInfo(`type`, token, name, image, lang)
  }
}

case class JsonGetAvatarInfo(`type`: String, token: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonGetAvatarInfo.`type`
}

object JsonGetAvatarInfo {
  implicit val jsonFormat: OFormat[JsonGetAvatarInfo] = Json.format[JsonGetAvatarInfo]

  val `type`: String = "getAvatar"
}
