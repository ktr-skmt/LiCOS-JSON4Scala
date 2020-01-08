package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.VillageValidation

final case class JsonChangeLanguage(`type`: String, language: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonChangeLanguage.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(language: String) = {
    this(JsonChangeLanguage.`type`, language)
  }
}

object JsonChangeLanguage {
  val `type`: String = "changeLanguage"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonChangeLanguage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "language").read[String](VillageValidation.language)
  )(JsonChangeLanguage.apply _)

  implicit val jsonWrites: OWrites[JsonChangeLanguage] = Json.writes[JsonChangeLanguage]
}
