package licos.json.element.village.character

import licos.bson.element.village.{BsonName, BsonUpdate}
import licos.bson.element.village.character.BsonCharacter
import licos.json.element.village.iri.CharacterContext
import licos.json.element.village.{JsonName, JsonUpdate}
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

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
    id:         Long,
    name:       JsonName,
    image:      String,
    isMine:     Boolean,
    status:     String,
    update:     JsonUpdate,
    isAChoice:  Boolean
) extends JsonAbstractCharacter(
      `@context`: String,
      `@id`:      String,
      id:         Long,
      name:       JsonName,
      image:      String
    ) {

  def this(
      `@id`:     String,
      id:        Long,
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
      id:                   Long,
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
      id:            Long,
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
  implicit val jsonFormat: OFormat[JsonCharacter] = Json.format[JsonCharacter]

  def apply(
      `@id`:     String,
      id:        Long,
      name:      JsonName,
      image:     String,
      isMine:    Boolean,
      status:    String,
      update:    JsonUpdate,
      isAChoice: Boolean
  ): JsonCharacter = {
    new JsonCharacter(
      `@id`:     String,
      id:        Long,
      name:      JsonName,
      image:     String,
      isMine:    Boolean,
      status:    String,
      update:    JsonUpdate,
      isAChoice: Boolean
    )
  }
}
