package licos.json.element.village.role

import java.util.{List => JList}

import licos.bson.element.village.BsonName
import licos.bson.element.village.character.BsonSimpleCharacter
import licos.bson.element.village.role.BsonResultRole
import licos.json.element.village.JsonName
import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.element.village.iri.RoleContext
import licos.json.validation.village.RoleValidation
import org.bson.types.ObjectId

import scala.collection.JavaConverters._

final case class JsonResultRole(
    `@context`:         String,
    `@id`:              String,
    isMine:             Boolean,
    name:               JsonName,
    image:              String,
    numberOfCharacters: Int,
    character:          Seq[JsonSimpleCharacter]
) extends JsonAbstractRole(
      `@context`: String,
      `@id`:      String,
      name:       JsonName,
      image:      String
    ) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@id`:              String,
      isMine:             Boolean,
      name:               JsonName,
      image:              String,
      numberOfCharacters: Int,
      character:          Seq[JsonSimpleCharacter]
  ) = {
    this(
      RoleContext.iri:    String,
      `@id`:              String,
      isMine:             Boolean,
      name:               JsonName,
      image:              String,
      numberOfCharacters: Int,
      character:          Seq[JsonSimpleCharacter]
    )
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@context`:         String,
      `@id`:              String,
      isMine:             Boolean,
      name:               JsonName,
      image:              String,
      numberOfCharacters: Int,
      character:          JList[JsonSimpleCharacter]
  ) = {
    this(
      `@context`:         String,
      `@id`:              String,
      isMine:             Boolean,
      name:               JsonName,
      image:              String,
      numberOfCharacters: Int,
      character.asScala:  Seq[JsonSimpleCharacter]
    )
  }

  def toBson: BsonResultRole = {
    new BsonResultRole(
      new ObjectId(),
      `@context`:                     String,
      `@id`:                          String,
      isMine:                         Boolean,
      name.toBson:                    BsonName,
      image:                          String,
      numberOfCharacters:             Int,
      character.map(_.toBson).asJava: JList[BsonSimpleCharacter]
    )
  }
}

object JsonResultRole {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonResultRole] = (
    (JsPath \ "@context").read[String](RoleValidation.`@context`) and
      (JsPath \ "@id").read[String](RoleValidation.`@id`) and
      (JsPath \ "isMine").read[Boolean] and
      (JsPath \ "name").read[JsonName] and
      (JsPath \ "image").read[String](RoleValidation.image) and
      (JsPath \ "numberOfCharacters").read[Int](RoleValidation.numberOfCharacters) and
      (JsPath \ "character").read[Seq[JsonSimpleCharacter]]
  )(JsonResultRole.apply _)

  implicit val jsonWrites: OWrites[JsonResultRole] = Json.writes[JsonResultRole]
}
