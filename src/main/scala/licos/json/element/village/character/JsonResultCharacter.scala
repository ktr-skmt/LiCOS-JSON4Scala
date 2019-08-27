package licos.json.element.village.character

import licos.bson.element.village.{BsonAvatar, BsonName}
import licos.bson.element.village.character.BsonResultCharacter
import licos.bson.element.village.role.BsonSimpleRole
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}
import licos.json.element.village.iri.CharacterContext
import licos.json.element.village.role.JsonSimpleRole
import licos.json.element.village.{JsonAvatar, JsonName}

/**
  * <pre>
  * Created on 2018/01/09.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonResultCharacter(`@context`: String,
                               `@id`: String,
                               id: Long,
                               name: JsonName,
                               image: String,
                               isMine: Boolean,
                               role: JsonSimpleRole,
                               status: String,
                               result: String,
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
           isMine: Boolean,
           role: JsonSimpleRole,
           status: String,
           result: String,
           avatar: JsonAvatar) = {
    this(
      CharacterContext.iri: String,
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      isMine: Boolean,
      role: JsonSimpleRole,
      status: String,
      result: String,
      avatar: JsonAvatar
    )
  }

  override def toBson: BsonResultCharacter = {
    new BsonResultCharacter(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      id: Long,
      name.toBson: BsonName,
      image: String,
      isMine: Boolean,
      role.toBson: BsonSimpleRole,
      status: String,
      result: String,
      avatar.toBson: BsonAvatar
    )
  }
}

object JsonResultCharacter {
  implicit val jsonFormat: OFormat[JsonResultCharacter] = Json.format[JsonResultCharacter]

  def apply(`@id`: String,
            id: Long,
            name: JsonName,
            image: String,
            isMine: Boolean,
            role: JsonSimpleRole,
            status: String,
            result: String,
            avatar: JsonAvatar): JsonResultCharacter = {
    new JsonResultCharacter(
      `@id`: String,
      id: Long,
      name: JsonName,
      image: String,
      isMine: Boolean,
      role: JsonSimpleRole,
      status: String,
      result: String,
      avatar: JsonAvatar
    )
  }
}