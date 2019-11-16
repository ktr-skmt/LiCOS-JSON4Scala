package licos.json.element.lobby

import licos.json.validation.lobby.UserValidation

final case class JsonChangeUserPassword(`type`: String, userPassword: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonChangeUserPassword.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(userPassword: String) = {
    this(JsonChangeUserPassword.`type`, userPassword)
  }
}

object JsonChangeUserPassword {

  val `type`: String = "changeUserPassword"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonChangeUserPassword] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "userPassword").read[String](UserValidation.password)
  )(JsonChangeUserPassword.apply _)

  implicit val jsonWrites: OWrites[JsonChangeUserPassword] = Json.writes[JsonChangeUserPassword]

}
