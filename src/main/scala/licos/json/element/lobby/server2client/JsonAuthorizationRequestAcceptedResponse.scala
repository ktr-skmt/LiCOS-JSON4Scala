package licos.json.element.lobby.server2client

import licos.json.element.lobby.TypeSystem
import licos.json.validation.auth.ResponseValidation
import licos.json.validation.village.AvatarValidation

final case class JsonAuthorizationRequestAcceptedResponse(`type`: String, accessToken: String, response: String)
    extends TypeSystem(`type`) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(accessToken: String, response: String) = {
    this(JsonAuthorizationRequestAcceptedResponse.`type`, accessToken, response)
  }

  override protected def validType: String = JsonAuthorizationRequestAcceptedResponse.`type`
}

object JsonAuthorizationRequestAcceptedResponse {
  val `type`: String = "authorizationRequestAcceptedResponse"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonAuthorizationRequestAcceptedResponse] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "accessToken").read[String](AvatarValidation.token) and
      (JsPath \ "response").read[String](ResponseValidation.server2client)
  )(JsonAuthorizationRequestAcceptedResponse.apply _)

  implicit val jsonWrites: OWrites[JsonAuthorizationRequestAcceptedResponse] =
    Json.writes[JsonAuthorizationRequestAcceptedResponse]
}
