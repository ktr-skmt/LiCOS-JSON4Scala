package licos.json.element.lobby.server2client

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.VillageValidation

final case class JsonPlayed(`type`: String, language: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonPlayed.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(language: String) = this(JsonPlayed.`type`, language)
}

object JsonPlayed {

  val `type`: String = "played"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonPlayed] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "language").read[String](VillageValidation.language)
  )(JsonPlayed.apply _)

  implicit val jsonWrites: OWrites[JsonPlayed] = Json.writes[JsonPlayed]

}
