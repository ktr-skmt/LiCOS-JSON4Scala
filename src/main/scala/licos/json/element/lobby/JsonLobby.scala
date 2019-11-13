package licos.json.element.lobby

import licos.json.element.village.JsonSubError
import licos.json.validation.lobby.LobbyValidation

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonLobby(`type`: String, lobby: String, villages: Seq[JsonVillage], error: Option[JsonSubError])
    extends TypeSystem(`type`) {
  override protected def validType: String = JsonLobby.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(lobby: String, village: Seq[JsonVillage], error: Option[JsonSubError]) = {
    this(JsonLobby.`type`, lobby, village, error)
  }
}

object JsonLobby {

  val `type`: String = "lobby"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonLobby] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "lobby").read[String](LobbyValidation.lobby) and
      (JsPath \ "villages").read[Seq[JsonVillage]] and
      (JsPath \ "error").readNullable[JsonSubError]
  )(JsonLobby.apply _)

  implicit val jsonWrites: OWrites[JsonLobby] = Json.writes[JsonLobby]
}
