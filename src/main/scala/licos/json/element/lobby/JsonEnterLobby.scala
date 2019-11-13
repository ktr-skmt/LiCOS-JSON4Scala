package licos.json.element.lobby

import licos.json.validation.lobby.LobbyValidation
import licos.json.validation.village.AvatarValidation

/**
  * <pre>
  * Created on 2018/01/04.
  * </pre>
  *
  * @author K.Sakamoto
  */
final case class JsonEnterLobby(`type`: String, token: String, lobby: String, page: Int) extends TypeSystem(`type`) {
  override protected def validType: String = JsonEnterLobby.`type`
}

object JsonEnterLobby {

  val `type`: String = "enterLobby"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonEnterLobby] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "lobby").read[String](LobbyValidation.lobby) and
      (JsPath \ "page").read[Int](LobbyValidation.page)
  )(JsonEnterLobby.apply _)

  implicit val jsonWrites: OWrites[JsonEnterLobby] = Json.writes[JsonEnterLobby]

}
