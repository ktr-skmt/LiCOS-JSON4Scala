package licos.json.element.village

import licos.json.element.village.iri.AvatarContext
import licos.json.validation.village.AvatarValidation

final case class JsonAvatar(`@context`: String, `@id`: String, token: String, name: String, image: String)
    extends JsonElement {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(`@id`: String, token: String, name: String, image: String) = {
    this(
      AvatarContext.iri: String,
      `@id`:             String,
      token:             String,
      name:              String,
      image:             String
    )
  }

}

object JsonAvatar {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonAvatar] = (
    (JsPath \ "@context").read[String](AvatarValidation.`@context`) and
      (JsPath \ "@id").read[String](AvatarValidation.`@id`) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "name").read[String](AvatarValidation.name) and
      (JsPath \ "image").read[String](AvatarValidation.image)
  )(JsonAvatar.apply _)

  implicit val jsonWrites: OWrites[JsonAvatar] = Json.writes[JsonAvatar]
}
