package licos.json.element.lobby

import licos.json.validation.auth.ResponseValidation
import licos.json.validation.village.AvatarValidation

final case class JsonAuthorizationRequestAcceptedResponse(`type`: String, accessToken: String, response: String) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(accessToken: String, response: String) = {
    this(JsonAuthorizationRequestAcceptedResponse.`type`, accessToken, response)
  }

}

object JsonAuthorizationRequestAcceptedResponse {
  val `type`: String = "authorizationRequestAcceptedResponse"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonAuthorizationRequestAcceptedResponse] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "accessToken").read[String](AvatarValidation.token) and
      (JsPath \ "response").read[String](ResponseValidation.server2robot)
  )(JsonAuthorizationRequestAcceptedResponse.apply _)

  implicit val jsonWrites: OWrites[JsonAuthorizationRequestAcceptedResponse] =
    Json.writes[JsonAuthorizationRequestAcceptedResponse]
}
