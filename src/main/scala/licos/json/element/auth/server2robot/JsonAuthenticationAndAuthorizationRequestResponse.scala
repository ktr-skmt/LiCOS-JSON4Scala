package licos.json.element.auth.server2robot

import licos.json.element.Element
import licos.json.element.lobby.TypeSystem
import licos.json.validation.auth.ResponseValidation
import licos.json.validation.village.AvatarValidation

final case class JsonAuthenticationAndAuthorizationRequestResponse(
    `type`:      String,
    accessToken: String,
    response:    String
) extends TypeSystem(`type`)
    with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(accessToken: String, response: String) = {
    this(JsonAuthenticationAndAuthorizationRequestResponse.`type`, accessToken, response)
  }

  override protected def validType: String = JsonAuthenticationAndAuthorizationRequestResponse.`type`
}

object JsonAuthenticationAndAuthorizationRequestResponse {

  val `type`: String = "authenticationAndAuthorizationRequestResponse"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonAuthenticationAndAuthorizationRequestResponse] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "accessToken").read[String](AvatarValidation.token) and
      (JsPath \ "response").read[String](ResponseValidation.server2client)
  )(JsonAuthenticationAndAuthorizationRequestResponse.apply _)

  implicit val jsonWrites: OWrites[JsonAuthenticationAndAuthorizationRequestResponse] =
    Json.writes[JsonAuthenticationAndAuthorizationRequestResponse]
}
