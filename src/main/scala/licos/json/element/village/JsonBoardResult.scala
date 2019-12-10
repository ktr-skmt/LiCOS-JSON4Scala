package licos.json.element.village

import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.validation.village.{BoardResultValidation, TimeValidation}

final case class JsonBoardResult(
    `@context`: String,
    `@id`:      String,
    character:  JsonSimpleCharacter,
    polarity:   String,
    phase:      String,
    day:        Int
) extends JsonElement

object JsonBoardResult {

  import play.api.libs.json._
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
