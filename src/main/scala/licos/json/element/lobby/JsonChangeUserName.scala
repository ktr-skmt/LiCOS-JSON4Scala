package licos.json.element.lobby

import licos.json.validation.lobby.UserValidation

final case class JsonChangeUserName(`type`: String, userName: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonChangeUserName.`type`
}

object JsonChangeUserName {

  val `type`: String = "changeUserName"

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonChangeUserName] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "userName").read[String](UserValidation.name)
  )(JsonChangeUserName.apply _)

  implicit val jsonWrites: OWrites[JsonChangeUserName] = Json.writes[JsonChangeUserName]
}
