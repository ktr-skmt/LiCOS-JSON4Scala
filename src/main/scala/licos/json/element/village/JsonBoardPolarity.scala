package licos.json.element.village

import licos.bson.element.village.character.BsonSimpleCharacter
import licos.bson.element.village.BsonBoardPolarity
import licos.json.element.village.character.JsonSimpleCharacter
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

final case class JsonBoardPolarity(
    `@context`: String,
    `@id`:      String,
    character:  JsonSimpleCharacter,
    polarity:   String,
    phase:      String,
    day:        Int
) extends JsonElement {
  override def toBson: BsonBoardPolarity = {
    new BsonBoardPolarity(
      new ObjectId(),
      `@context`:       String,
      `@id`:            String,
      character.toBson: BsonSimpleCharacter,
      polarity:         String,
      phase:            String,
      day:              Int
    )
  }
}

object JsonBoardPolarity {
  implicit val jsonFormat: OFormat[JsonBoardPolarity] = Json.format[JsonBoardPolarity]
}
