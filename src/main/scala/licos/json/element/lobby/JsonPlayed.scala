package licos.json.element.lobby

import licos.json.validation.village.VillageValidation

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonPlayed(`type`: String, lang: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonPlayed.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(lang: String) = this(JsonPlayed.`type`, lang)
}

object JsonPlayed {

  val `type`: String = "played"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonPlayed] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "language").read[String](VillageValidation.language)
  )(JsonPlayed.apply _)

  implicit val jsonWrites: OWrites[JsonPlayed] = Json.writes[JsonPlayed]

}
