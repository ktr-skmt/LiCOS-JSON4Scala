package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonCreateOnymousAudience(`type`: String, name: String, image: String, language: String)
    extends TypeSystem(`type`) {

  override protected def validType: String = JsonCreateOnymousAudience.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(name: String, image: String, language: String) = {
    this(
      JsonCreateOnymousAudience.`type`,
      name,
      image,
      language
    )
  }
}

object JsonCreateOnymousAudience {

  val `type`: String = "createOnymousAudience"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonCreateOnymousAudience] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "name").read[String](AvatarValidation.name) and
      (JsPath \ "image").read[String](AvatarValidation.image) and
      (JsPath \ "language").read[String](VillageValidation.language)
  )(JsonCreateOnymousAudience.apply _)

  implicit val jsonWrites: OWrites[JsonCreateOnymousAudience] = Json.writes[JsonCreateOnymousAudience]
}
