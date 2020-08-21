package licos.json.element.village.character

import licos.json.element.village.iri.CharacterContext
import licos.json.element.village.role.JsonSimpleRole
import licos.json.element.village.JsonName
import licos.json.validation.village.{ArchitectureValidation, CharacterValidation}

final case class JsonStatusCharacter(
    `@context`: String,
    `@id`:      String,
    id:         Int,
    name:       JsonName,
    image:      String,
    role:       JsonSimpleRole,
    status:     String,
    playerType: String
) extends JsonAbstractCharacter(
      `@context`: String,
      `@id`:      String,
      id:         Int,
      name:       JsonName,
      image:      String
    ) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@id`:      String,
      id:         Int,
      name:       JsonName,
      image:      String,
      role:       JsonSimpleRole,
      status:     String,
      playerType: String
  ) = {
    this(
      CharacterContext.iri: String,
      `@id`:                String,
      id:                   Int,
      name:                 JsonName,
      image:                String,
      role:                 JsonSimpleRole,
      status:               String,
      playerType:           String
    )
  }
}

object JsonStatusCharacter {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonStatusCharacter] = (
    (JsPath \ "@context").read[String](CharacterValidation.`@context`) and
      (JsPath \ "@id").read[String](CharacterValidation.`@id`) and
      (JsPath \ "id").read[Int](CharacterValidation.id) and
      (JsPath \ "name").read[JsonName] and
      (JsPath \ "image").read[String](CharacterValidation.image) and
      (JsPath \ "role").read[JsonSimpleRole] and
      (JsPath \ "status").read[String](CharacterValidation.status) and
      (JsPath \ "playerType").read[String](ArchitectureValidation.playerType)
  )(JsonStatusCharacter.apply _)

  implicit val jsonWrites: OWrites[JsonStatusCharacter] = Json.writes[JsonStatusCharacter]

}
