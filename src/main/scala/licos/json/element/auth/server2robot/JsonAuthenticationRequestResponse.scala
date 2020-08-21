package licos.json.element.auth.server2robot

import licos.json.element.lobby.TypeSystem
import licos.json.validation.auth.ResponseValidation
import licos.json.validation.village.AvatarValidation

final case class JsonAuthenticationRequestResponse(`type`: String, accessToken: String, response: String)
    extends TypeSystem(`type`) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(accessToken: String, response: String) = {
    this(JsonAuthenticationRequestResponse.`type`, accessToken, response)
  }

  override protected def validType: String = JsonAuthenticationRequestResponse.`type`

}

object JsonAuthenticationRequestResponse {

  val `type`: String = "authenticationRequestResponse"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonAuthenticationRequestResponse] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "accessToken").read[String](AvatarValidation.token) and
      (JsPath \ "response").read[String](ResponseValidation.server2robot.authenticationRequestResponse)
  )(JsonAuthenticationRequestResponse.apply _)

  implicit val jsonWrites: OWrites[JsonAuthenticationRequestResponse] =
    Json.writes[JsonAuthenticationRequestResponse]
}
