package licos.json.element.lobby.server2client

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonAuthorizationRequest(`type`: String, accessToken: String) extends TypeSystem(`type`) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(accessToken: String) = {
    this(JsonAuthorizationRequest.`type`, accessToken)
  }

  override protected def validType: String = JsonAuthorizationRequest.`type`
}

object JsonAuthorizationRequest {

  val `type`: String = "authorizationRequest"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonAuthorizationRequest] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "accessToken").read[String](AvatarValidation.token)
  )(JsonAuthorizationRequest.apply _)

  implicit val jsonWrites: OWrites[JsonAuthorizationRequest] = Json.writes[JsonAuthorizationRequest]

}
