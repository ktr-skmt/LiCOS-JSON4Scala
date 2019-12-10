package licos.json.element.village.character

import licos.json.element.village.iri.CharacterContext
import licos.json.element.village.role.JsonSimpleRole
import licos.json.element.village.{JsonAvatar, JsonName}
import licos.json.validation.village.CharacterValidation

final case class JsonResultCharacter(
    `@context`: String,
    `@id`:      String,
    id:         Int,
    name:       JsonName,
    image:      String,
    isMine:     Boolean,
    role:       JsonSimpleRole,
    status:     String,
    result:     String,
    avatar:     JsonAvatar
) extends JsonAbstractCharacter(
      `@context`: String,
      `@id`:      String,
      id:         Int,
      name:       JsonName,
      image:      String
    ) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@id`:  String,
      id:     Int,
      name:   JsonName,
      image:  String,
      isMine: Boolean,
      role:   JsonSimpleRole,
      status: String,
      result: String,
      avatar: JsonAvatar
  ) = {
    this(
      CharacterContext.iri: String,
      `@id`:                String,
      id:                   Int,
      name:                 JsonName,
      image:                String,
      isMine:               Boolean,
      role:                 JsonSimpleRole,
      status:               String,
      result:               String,
      avatar:               JsonAvatar
    )
  }
}

object JsonResultCharacter {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonResultCharacter] = (
    (JsPath \ "@context").read[String](CharacterValidation.`@context`) and
      (JsPath \ "@id").read[String](CharacterValidation.`@id`) and
      (JsPath \ "id").read[Int](CharacterValidation.id) and
      (JsPath \ "name").read[JsonName] and
      (JsPath \ "image").read[String](CharacterValidation.image) and
      (JsPath \ "isMine").read[Boolean] and
      (JsPath \ "role").read[JsonSimpleRole] and
      (JsPath \ "status").read[String](CharacterValidation.status) and
      (JsPath \ "result").read[String](CharacterValidation.result) and
      (JsPath \ "avatar").read[JsonAvatar]
  )(JsonResultCharacter.apply _)

  implicit val jsonWrites: OWrites[JsonResultCharacter] = Json.writes[JsonResultCharacter]

}
