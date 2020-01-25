package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonChangeAvatar(
    `type`:   String,
    token:    String,
    name:     Option[String],
    image:    Option[String],
    language: Option[String]
) extends TypeSystem(`type`) {

  override protected def validType: String = JsonChangeAvatar.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, name: Option[String], image: Option[String], language: Option[String]) = {
    this(JsonChangeAvatar.`type`, token, name, image, language)
  }

}

object JsonChangeAvatar {

  val `type`: String = "changeAvatar"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonChangeAvatar] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "name").readNullable[String] and
      (JsPath \ "image").readNullable[String] and
      (JsPath \ "language").readNullable[String]
  )(JsonChangeAvatar.apply _)

  implicit val jsonWrites: OWrites[JsonChangeAvatar] = Json.writes[JsonChangeAvatar]

}
