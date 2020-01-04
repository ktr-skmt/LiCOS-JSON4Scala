package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonCreateOnymousAudience(`type`: String, name: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonCreateOnymousAudience.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(name: String) = {
    this(
      JsonCreateOnymousAudience.`type`,
      name
    )
  }
}

object JsonCreateOnymousAudience {

  val `type`: String = "createOnymousAudience"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonCreateOnymousAudience] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "name").read[String](AvatarValidation.name)
  )(JsonCreateOnymousAudience.apply _)

  implicit val jsonWrites: OWrites[JsonCreateOnymousAudience] = Json.writes[JsonCreateOnymousAudience]
}
