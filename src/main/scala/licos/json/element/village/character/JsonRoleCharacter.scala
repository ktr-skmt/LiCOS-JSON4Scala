package licos.json.element.village.character

import licos.bson.element.village.BsonName
import licos.bson.element.village.character.BsonRoleCharacter
import licos.bson.element.village.role.BsonSimpleRole
import licos.json.element.village.JsonName
import licos.json.element.village.iri.CharacterContext
import licos.json.element.village.role.JsonSimpleRole
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

/**
  * <pre>
  * Created on 2018/01/10.
  * </pre>
  *
  * @author K.Sakamoto
  */
final case class JsonRoleCharacter(
    `@context`: String,
    `@id`:      String,
    id:         Long,
    name:       JsonName,
    image:      String,
    role:       JsonSimpleRole
) extends JsonAbstractCharacter(
      `@context`: String,
      `@id`:      String,
      id:         Long,
      name:       JsonName,
      image:      String
    ) {

  def this(`@id`: String, id: Long, name: JsonName, image: String, role: JsonSimpleRole) = {
    this(
      CharacterContext.iri: String,
      `@id`:                String,
      id:                   Long,
      name:                 JsonName,
      image:                String,
      role:                 JsonSimpleRole
    )
  }

  override def toBson: BsonRoleCharacter = {
    new BsonRoleCharacter(
      new ObjectId(),
      `@context`:  String,
      `@id`:       String,
      id:          Long,
      name.toBson: BsonName,
      image:       String,
      role.toBson: BsonSimpleRole
    )
  }
}

object JsonRoleCharacter {
  implicit val jsonFormat: OFormat[JsonRoleCharacter] = Json.format[JsonRoleCharacter]

  def apply(`@id`: String, id: Long, name: JsonName, image: String, role: JsonSimpleRole): JsonRoleCharacter = {
    new JsonRoleCharacter(
      `@id`: String,
      id:    Long,
      name:  JsonName,
      image: String,
      role:  JsonSimpleRole
    )
  }
}
