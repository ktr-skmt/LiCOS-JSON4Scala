package licos.json.element.lobby.server2client

import licos.json.element.lobby.TypeSystem
import licos.json.validation.lobby.UserValidation
import licos.json.validation.village.VillageValidation

final case class JsonSettings(`type`: String, userName: String, userEmail: String, language: String)
    extends TypeSystem(`type`) {
  override protected def validType: String = JsonSettings.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(userName: String, userEmail: String, language: String) = {
    this(JsonSettings.`type`, userName, userEmail, language)
  }
}

object JsonSettings {

  val `type`: String = "settings"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.{email, pattern}
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonSettings] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "userName").read[String](UserValidation.name) and
      (JsPath \ "userEmail").read[String](email) and
      (JsPath \ "language").read[String](VillageValidation.language)
  )(JsonSettings.apply _)

  implicit val jsonWrites: OWrites[JsonSettings] = Json.writes[JsonSettings]

}
