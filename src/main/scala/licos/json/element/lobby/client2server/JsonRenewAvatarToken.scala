package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.lobby.LobbyValidation
import licos.json.validation.village.AvatarValidation

final case class JsonRenewAvatarToken(`type`: String, token: String, lobby: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonRenewAvatarToken.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, lobby: String) = {
    this(JsonRenewAvatarToken.`type`, token, lobby)
  }

}

object JsonRenewAvatarToken {

  val `type`: String = "renewAvatarToken"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonRenewAvatarToken] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "lobby").read[String](LobbyValidation.lobby)
  )(JsonRenewAvatarToken.apply _)

  implicit val jsonWrites: OWrites[JsonRenewAvatarToken] = Json.writes[JsonRenewAvatarToken]
}
