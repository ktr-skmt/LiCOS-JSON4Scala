package licos.json.element.village

import licos.json.validation.village.TimeValidation

final case class JsonUpdate(`@id`: String, phase: String, day: Int) extends JsonElement

object JsonUpdate {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonUpdate] = (
    (JsPath \ "@id").read[String](TimeValidation.`@id`) and
      (JsPath \ "phase").read[String](TimeValidation.phase) and
      (JsPath \ "day").read[Int](TimeValidation.day)
  )(JsonUpdate.apply _)

  implicit val jsonWrites: OWrites[JsonUpdate] = Json.writes[JsonUpdate]
}
