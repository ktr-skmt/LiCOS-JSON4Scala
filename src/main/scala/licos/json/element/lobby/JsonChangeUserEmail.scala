package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonChangeUserEmail(`type`: String,
                               userEmail: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonChangeUserEmail.`type`
}

object JsonChangeUserEmail {
  implicit val jsonFormat: OFormat[JsonChangeUserEmail] = Json.format[JsonChangeUserEmail]

  val `type`: String = "changeUserEmail"
}