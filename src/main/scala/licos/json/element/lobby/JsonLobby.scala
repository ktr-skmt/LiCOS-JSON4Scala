package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonLobby(`type`: String,
                     lobby: String,
                     villages: Seq[JsonVillage],
                     error: Option[String]) extends TypeSystem(`type`) {
  override protected def validType: String = JsonLobby.`type`
}

object JsonLobby {
  implicit val jsonFormat: OFormat[JsonLobby] = Json.format[JsonLobby]

  val `type`: String = "lobby"

  def generate(lobby: String,
               village: Seq[JsonVillage],
               error: Option[String]): JsonLobby = {
    new JsonLobby(`type`, lobby, village, error)
  }
}