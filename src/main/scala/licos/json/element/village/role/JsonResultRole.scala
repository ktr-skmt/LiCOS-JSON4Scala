package licos.json.element.village.role

import java.util.{List => JList}

import licos.bson.element.village.BsonName
import licos.bson.element.village.character.BsonSimpleCharacter
import licos.bson.element.village.role.BsonResultRole
import licos.json.element.village.JsonName
import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.element.village.iri.RoleContext
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

import scala.collection.JavaConverters._

/**
  * <pre>
  * Created on 2018/01/09.
  * </pre>
  *
  * @author K.Sakamoto
  */
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
  implicit val jsonFormat: OFormat[JsonResultRole] = Json.format[JsonResultRole]

  def apply(
      `@id`:              String,
      isMine:             Boolean,
      name:               JsonName,
      image:              String,
      numberOfCharacters: Int,
      character:          Seq[JsonSimpleCharacter]
  ): JsonResultRole = {
    new JsonResultRole(
      `@id`:              String,
      isMine:             Boolean,
      name:               JsonName,
      image:              String,
      numberOfCharacters: Int,
      character:          Seq[JsonSimpleCharacter]
    )
  }
}
