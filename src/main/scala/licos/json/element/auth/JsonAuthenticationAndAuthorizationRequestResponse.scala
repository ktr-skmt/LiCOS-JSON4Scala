package licos.json.element.auth

import licos.json.validation.auth.ResponseValidation
import licos.json.validation.village.AvatarValidation

final case class JsonAuthenticationAndAuthorizationRequestResponse(
    `type`:      String,
    accessToken: String,
    response:    String
) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(accessToken: String, response: String) = {
    this(JsonAuthenticationAndAuthorizationRequestResponse.`type`, accessToken, response)
  }

}

object JsonAuthenticationAndAuthorizationRequestResponse {

  val `type`: String = "authenticationAndAuthorizationRequestResponse"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonAuthenticationAndAuthorizationRequestResponse] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "accessToken").read[String](AvatarValidation.token) and
      (JsPath \ "response").read[String](ResponseValidation.server2client)
  )(JsonAuthenticationAndAuthorizationRequestResponse.apply _)

  implicit val jsonWrites: OWrites[JsonAuthenticationAndAuthorizationRequestResponse] =
    Json.writes[JsonAuthenticationAndAuthorizationRequestResponse]
}
