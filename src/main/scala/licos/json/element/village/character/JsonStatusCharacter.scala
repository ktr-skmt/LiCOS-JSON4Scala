package licos.json.element.village.character

import licos.bson.element.village.BsonName
import licos.bson.element.village.character.BsonStatusCharacter
import licos.bson.element.village.role.BsonSimpleRole
import licos.json.element.village.iri.CharacterContext
import licos.json.element.village.role.JsonSimpleRole
import licos.json.element.village.JsonName
import licos.json.validation.village.CharacterValidation
import org.bson.types.ObjectId

final case class JsonStatusCharacter(
    `@context`:    String,
    `@id`:         String,
    id:            Int,
    name:          JsonName,
    image:         String,
    role:          JsonSimpleRole,
    status:        String,
    isHumanPlayer: Boolean
) extends JsonAbstractCharacter(
      `@context`: String,
      `@id`:      String,
      id:         Int,
      name:       JsonName,
      image:      String
    ) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@id`:         String,
      id:            Int,
      name:          JsonName,
      image:         String,
      role:          JsonSimpleRole,
      status:        String,
      isHumanPlayer: Boolean
  ) = {
    this(
      CharacterContext.iri: String,
      `@id`:                String,
      id:                   Int,
      name:                 JsonName,
      image:                String,
      role:                 JsonSimpleRole,
      status:               String,
      isHumanPlayer:        Boolean
    )
  }

  override def toBson: BsonStatusCharacter = {
    new BsonStatusCharacter(
      new ObjectId(),
      `@context`:    String,
      `@id`:         String,
      id:            Int,
      name.toBson:   BsonName,
      image:         String,
      role.toBson:   BsonSimpleRole,
      status:        String,
      isHumanPlayer: Boolean
    )
  }
}

object JsonStatusCharacter {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonStatusCharacter] = (
    (JsPath \ "@context").read[String](CharacterValidation.`@context`) and
      (JsPath \ "@id").read[String](CharacterValidation.`@id`) and
      (JsPath \ "id").read[Int](CharacterValidation.id) and
      (JsPath \ "name").read[JsonName] and
      (JsPath \ "image").read[String](CharacterValidation.image) and
      (JsPath \ "role").read[JsonSimpleRole] and
      (JsPath \ "status").read[String](CharacterValidation.status) and
      (JsPath \ "isHumanPlayer").read[Boolean]
  )(JsonStatusCharacter.apply _)

  implicit val jsonWrites: OWrites[JsonStatusCharacter] = Json.writes[JsonStatusCharacter]

}
