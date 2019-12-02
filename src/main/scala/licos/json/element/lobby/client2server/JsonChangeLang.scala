package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.VillageValidation

final case class JsonChangeLang(`type`: String, lang: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonChangeLang.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(lang: String) = {
    this(JsonChangeLang.`type`, lang)
  }
}

object JsonChangeLang {
  val `type`: String = "changeLang"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonChangeLang] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "lang").read[String](VillageValidation.language)
  )(JsonChangeLang.apply _)

  implicit val jsonWrites: OWrites[JsonChangeLang] = Json.writes[JsonChangeLang]
}
