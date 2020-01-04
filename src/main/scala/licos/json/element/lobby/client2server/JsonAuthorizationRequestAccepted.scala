package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonAuthorizationRequestAccepted(`type`: String, accessToken: String) extends TypeSystem(`type`) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(accessToken: String) = {
    this(JsonAuthorizationRequestAccepted.`type`, accessToken)
  }

  override protected def validType: String = JsonAuthorizationRequestAccepted.`type`
}

object JsonAuthorizationRequestAccepted {

  val `type`: String = "authorizationRequestAccepted"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonAuthorizationRequestAccepted] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "accessToken").read[String](AvatarValidation.token)
  )(JsonAuthorizationRequestAccepted.apply _)

  implicit val jsonWrites: OWrites[JsonAuthorizationRequestAccepted] = Json.writes[JsonAuthorizationRequestAccepted]

}
