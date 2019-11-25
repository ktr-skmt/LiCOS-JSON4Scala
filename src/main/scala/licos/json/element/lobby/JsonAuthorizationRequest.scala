package licos.json.element.lobby

import licos.json.validation.village.AvatarValidation

final case class JsonAuthorizationRequest(`type`: String, accessToken: String) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(accessToken: String) = {
    this(JsonAuthorizationRequest.`type`, accessToken)
  }

}

object JsonAuthorizationRequest {

  val `type`: String = "authorizationRequest"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonAuthorizationRequest] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "accessToken").read[String](AvatarValidation.token)
  )(JsonAuthorizationRequest.apply _)

  implicit val jsonWrites: OWrites[JsonAuthorizationRequest] = Json.writes[JsonAuthorizationRequest]

}
