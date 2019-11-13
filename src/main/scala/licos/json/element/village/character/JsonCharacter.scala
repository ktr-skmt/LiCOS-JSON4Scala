package licos.json.element.village.character

import licos.bson.element.village.{BsonName, BsonUpdate}
import licos.bson.element.village.character.BsonCharacter
import licos.json.element.village.iri.CharacterContext
import licos.json.element.village.{JsonName, JsonUpdate}
import licos.json.validation.village.CharacterValidation
import org.bson.types.ObjectId

/**
  * <pre>
  * Created on 2018/01/09.
  * </pre>
  *
  * @author K.Sakamoto
  */
final case class JsonCharacter(
    `@context`: String,
    `@id`:      String,
    id:         Int,
    name:       JsonName,
    image:      String,
    isMine:     Boolean,
    status:     String,
    update:     JsonUpdate,
    isAChoice:  Boolean
) extends JsonAbstractCharacter(
      `@context`: String,
      `@id`:      String,
      id:         Int,
      name:       JsonName,
      image:      String
    ) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@id`:     String,
      id:        Int,
      name:      JsonName,
      image:     String,
      isMine:    Boolean,
      status:    String,
      update:    JsonUpdate,
      isAChoice: Boolean
  ) = {
    this(
      CharacterContext.iri: String,
      `@id`:                String,
      id:                   Int,
      name:                 JsonName,
      image:                String,
      isMine:               Boolean,
      status:               String,
      update:               JsonUpdate,
      isAChoice:            Boolean
    )
  }

  override def toBson: BsonCharacter = {
    new BsonCharacter(
      new ObjectId(),
      `@context`:    String,
      `@id`:         String,
      id:            Int,
      name.toBson:   BsonName,
      image:         String,
      isMine:        Boolean,
      status:        String,
      update.toBson: BsonUpdate,
      isAChoice:     Boolean
    )
  }
}

object JsonCharacter {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonCharacter] = (
    (JsPath \ "@context").read[String](CharacterValidation.`@context`) and
      (JsPath \ "@id").read[String](CharacterValidation.`@id`) and
      (JsPath \ "id").read[Int](CharacterValidation.id) and
      (JsPath \ "name").read[JsonName] and
      (JsPath \ "image").read[String](CharacterValidation.image) and
      (JsPath \ "isMine").read[Boolean] and
      (JsPath \ "status").read[String](CharacterValidation.status) and
      (JsPath \ "update").read[JsonUpdate] and
      (JsPath \ "isAChoice").read[Boolean]
  )(JsonCharacter.apply _)

  implicit val jsonWrites: OWrites[JsonCharacter] = Json.writes[JsonCharacter]

}
