package licos.json.element.lobby

import licos.json.validation.village.VillageValidation

final case class JsonPlayed(`type`: String, lang: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonPlayed.`type`
}

object JsonPlayed {

  def generate(lang: String): JsonPlayed = JsonPlayed(`type`, lang)

  val `type`: String = "played"

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonPlayed] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "lang").read[String](VillageValidation.lang)
  )(JsonPlayed.apply _)

  implicit val jsonWrites: OWrites[JsonPlayed] = Json.writes[JsonPlayed]

}
