package licos.json.element.lobby

import licos.json.validation.lobby.UserValidation
import licos.json.validation.village.VillageValidation

final case class JsonSettings(`type`: String, userName: String, userEmail: String, lang: String)
    extends TypeSystem(`type`) {
  override protected def validType: String = JsonSettings.`type`
}

object JsonSettings {

  val `type`: String = "settings"

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonSettings] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "userName").read[String](UserValidation.name) and
      (JsPath \ "userEmail").read[String](email) and
      (JsPath \ "lang").read[String](VillageValidation.lang)
  )(JsonSettings.apply _)

  implicit val jsonWrites: OWrites[JsonSettings] = Json.writes[JsonSettings]

  def generate(userName: String, userEmail: String, lang: String): JsonSettings = {
    JsonSettings(`type`, userName, userEmail, lang)
  }
}
