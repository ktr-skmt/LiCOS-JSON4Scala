package licos.json.element.lobby.server2client

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonNewAvatarToken(`type`: String, token: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonNewAvatarToken.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String) = {
    this(JsonNewAvatarToken.`type`, token)
  }

}

object JsonNewAvatarToken {

  val `type`: String = "newAvatarToken"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonNewAvatarToken] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token)
  )(JsonNewAvatarToken.apply _)

  implicit val jsonWrites: OWrites[JsonNewAvatarToken] = Json.writes[JsonNewAvatarToken]
}
