package licos.json.element.auth.server2robot

import licos.json.element.lobby.TypeSystem
import licos.json.validation.auth.ResponseValidation
import licos.json.validation.village.AvatarValidation

final case class JsonAuthorizationRequestResponse(`type`: String, accessToken: String, response: String)
    extends TypeSystem(`type`) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(accessToken: String, response: String) = {
    this(JsonAuthorizationRequestResponse.`type`, accessToken, response)
  }

  override protected def validType: String = JsonAuthorizationRequestResponse.`type`

}

object JsonAuthorizationRequestResponse {

  val `type`: String = "authorizationRequestResponse"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonAuthorizationRequestResponse] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "accessToken").read[String](AvatarValidation.token) and
      (JsPath \ "response").read[String](ResponseValidation.server2robot.authorizationRequestResponse)
  )(JsonAuthorizationRequestResponse.apply _)

  implicit val jsonWrites: OWrites[JsonAuthorizationRequestResponse] =
    Json.writes[JsonAuthorizationRequestResponse]

}
