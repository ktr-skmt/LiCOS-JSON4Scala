package licos.json.element.lobby

import licos.json.validation.lobby.{IdSearchValidation, LobbyValidation}
import licos.json.validation.village.AvatarValidation

final case class JsonIdSearch(`type`: String, token: String, lobby: String, idForSearching: Int)
    extends TypeSystem(`type`) {
  override protected def validType: String = JsonIdSearch.`type`
}

object JsonIdSearch {

  val `type`: String = "idSearch"

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonIdSearch] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "lobby").read[String](LobbyValidation.lobby) and
      (JsPath \ "idForSearching").read[Int](IdSearchValidation.idForSearching)
  )(JsonIdSearch.apply _)

  implicit val jsonWrites: OWrites[JsonIdSearch] = Json.writes[JsonIdSearch]
}
