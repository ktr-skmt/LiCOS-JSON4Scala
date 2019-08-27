package licos.json.element.village.character

import licos.bson.element.village.{BsonAvatar, BsonName}
import licos.bson.element.village.character.BsonStatusCharacter
import licos.bson.element.village.role.BsonSimpleRole
import licos.json.element.village.iri.CharacterContext
import licos.json.element.village.role.JsonSimpleRole
import licos.json.element.village.{JsonAvatar, JsonName}
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

case class JsonStatusCharacter(`@context`: String,
                               `@id`: String,
                               id: Long,
                               name: JsonName,
                               image: String,
                               role: JsonSimpleRole,
                               status: String,
                               avatar: JsonAvatar)
  extends JsonAbstractCharacter(
    `@context`: String,
    `@id`: String,
    id: Long,
    name: JsonName,
    image: String
  ) {

  def this(`@id`: String,
           id: Long,
           name: JsonName,
           image: String,
           role: JsonSimpleRole,
           status: String,
           avatar: JsonAvatar) = {
    this(
      CharacterContext.iri: String,
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      role: JsonSimpleRole,
      status: String,
      avatar: JsonAvatar
    )
  }
  override def toBson: BsonStatusCharacter = {
    new BsonStatusCharacter(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      id: Long,
      name.toBson: BsonName,
      image: String,
      role.toBson: BsonSimpleRole,
      status: String,
      avatar.toBson: BsonAvatar
    )
  }
}

object JsonStatusCharacter {
  implicit val jsonFormat: OFormat[JsonStatusCharacter] = Json.format[JsonStatusCharacter]

  def apply(`@id`: String,
            id: Long,
            name: JsonName,
            image: String,
            role: JsonSimpleRole,
            status: String,
            avatar: JsonAvatar): JsonStatusCharacter = {
    new JsonStatusCharacter(
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      role: JsonSimpleRole,
      status: String,
      avatar: JsonAvatar
    )
  }
}
