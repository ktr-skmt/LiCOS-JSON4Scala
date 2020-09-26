package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.lobby.{IdSearchValidation, LobbyValidation}
import licos.json.validation.village.AvatarValidation

final case class JsonIdSearch(`type`: String, token: String, lobby: String, idForSearching: Long)
    extends TypeSystem(`type`) {

  override protected def validType: String = JsonIdSearch.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, lobby: String, idForSearching: Long) = {
    this(JsonIdSearch.`type`, token, lobby, idForSearching)
  }
}

object JsonIdSearch {

  val `type`: String = "idSearch"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonIdSearch] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "lobby").read[String](LobbyValidation.lobby) and
      (JsPath \ "idForSearching").read[Long](IdSearchValidation.idForSearching)
  )(JsonIdSearch.apply _)

  implicit val jsonWrites: OWrites[JsonIdSearch] = Json.writes[JsonIdSearch]
}
