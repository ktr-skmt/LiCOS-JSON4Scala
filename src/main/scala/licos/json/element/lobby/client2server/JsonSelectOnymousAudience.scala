package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonSelectOnymousAudience(`type`: String, token: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonSelectOnymousAudience.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String) = {
    this(JsonSelectOnymousAudience.`type`, token)
  }

}

object JsonSelectOnymousAudience {

  val `type`: String = "selectOnymousAudience"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonSelectOnymousAudience] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token)
  )(JsonSelectOnymousAudience.apply _)

  implicit val jsonWrites: OWrites[JsonSelectOnymousAudience] = Json.writes[JsonSelectOnymousAudience]

}
