package licos.json.element.village

import licos.bson.element.village.BsonUpdate
import licos.json.validation.village.TimeValidation
import org.bson.types.ObjectId

final case class JsonUpdate(`@id`: String, phase: String, day: Int) extends JsonElement {
  override def toBson: BsonUpdate = {
    new BsonUpdate(
      new ObjectId(),
      `@id`: String,
      phase: String,
      day:   Int
    )
  }
}

object JsonUpdate {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonUpdate] = (
    (JsPath \ "@id").read[String](TimeValidation.`@id`) and
      (JsPath \ "phase").read[String](TimeValidation.phase) and
      (JsPath \ "day").read[Int](TimeValidation.day)
  )(JsonUpdate.apply _)

  implicit val jsonWrites: OWrites[JsonUpdate] = Json.writes[JsonUpdate]
}
