package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.lobby.LobbyValidation
import licos.json.validation.village.AvatarValidation

final case class JsonEnterLobby(`type`: String, token: String, lobby: String, page: Int) extends TypeSystem(`type`) {

  override protected def validType: String = JsonEnterLobby.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, lobby: String, page: Int) = {
    this(JsonEnterLobby.`type`, token, lobby, page)
  }
}

object JsonEnterLobby {

  val `type`: String = "enterLobby"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonEnterLobby] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "lobby").read[String](LobbyValidation.lobby) and
      (JsPath \ "page").read[Int](LobbyValidation.page)
  )(JsonEnterLobby.apply _)

  implicit val jsonWrites: OWrites[JsonEnterLobby] = Json.writes[JsonEnterLobby]

}
