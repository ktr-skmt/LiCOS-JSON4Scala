package licos.json.element.village.character

import licos.bson.element.village.BsonName
import licos.bson.element.village.character.BsonStatusCharacter
import licos.bson.element.village.role.BsonSimpleRole
import licos.json.element.village.iri.CharacterContext
import licos.json.element.village.role.JsonSimpleRole
import licos.json.element.village.JsonName
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

final case class JsonStatusCharacter(
    `@context`:    String,
    `@id`:         String,
    id:            Long,
    name:          JsonName,
    image:         String,
    role:          JsonSimpleRole,
    status:        String,
    isHumanPlayer: Boolean
) extends JsonAbstractCharacter(
      `@context`: String,
      `@id`:      String,
      id:         Long,
      name:       JsonName,
      image:      String
    ) {

  def this(
      `@id`:         String,
      id:            Long,
      name:          JsonName,
      image:         String,
      role:          JsonSimpleRole,
      status:        String,
      isHumanPlayer: Boolean
  ) = {
    this(
      CharacterContext.iri: String,
      `@id`:                String,
      id:                   Long,
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
      id:            Long,
      name.toBson:   BsonName,
      image:         String,
      role.toBson:   BsonSimpleRole,
      status:        String,
      isHumanPlayer: Boolean
    )
  }
}

object JsonStatusCharacter {
  implicit val jsonFormat: OFormat[JsonStatusCharacter] = Json.format[JsonStatusCharacter]

  def apply(
      `@id`:         String,
      id:            Long,
      name:          JsonName,
      image:         String,
      role:          JsonSimpleRole,
      status:        String,
      isHumanPlayer: Boolean
  ): JsonStatusCharacter = {
    new JsonStatusCharacter(
      `@id`:         String,
      id:            Long,
      name:          JsonName,
      image:         String,
      role:          JsonSimpleRole,
      status:        String,
      isHumanPlayer: Boolean
    )
  }
}
