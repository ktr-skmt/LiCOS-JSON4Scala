package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonChangeUserPassword(`type`: String,
                                  userPassword: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonChangeUserPassword.`type`
}

object JsonChangeUserPassword {
  implicit val jsonFormat: OFormat[JsonChangeUserPassword] = Json.format[JsonChangeUserPassword]

  val `type`: String = "changeUserPassword"
}
