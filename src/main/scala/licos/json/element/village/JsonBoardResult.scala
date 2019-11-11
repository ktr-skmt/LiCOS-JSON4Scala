package licos.json.element.village

import licos.bson.element.village.character.BsonSimpleCharacter
import licos.bson.element.village.BsonBoardResult
import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.validation.village.{BoardResultValidation, TimeValidation}
import org.bson.types.ObjectId

final case class JsonBoardResult(
    `@context`: String,
    `@id`:      String,
    character:  JsonSimpleCharacter,
    polarity:   String,
    phase:      String,
    day:        Int
) extends JsonElement {

  override def toBson: BsonBoardResult = {
    new BsonBoardResult(
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

object JsonBoardResult {

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonBoardResult] = (
    (JsPath \ "@context").read[String](BoardResultValidation.`@context`) and
      (JsPath \ "@id").read[String](BoardResultValidation.`@id`) and
      (JsPath \ "character").read[JsonSimpleCharacter] and
      (JsPath \ "polarity").read[String](BoardResultValidation.polarity) and
      (JsPath \ "phase").read[String](TimeValidation.phase) and
      (JsPath \ "day").read[Int](TimeValidation.day)
  )(JsonBoardResult.apply _)

  implicit val jsonWrites: OWrites[JsonBoardResult] = Json.writes[JsonBoardResult]
}
