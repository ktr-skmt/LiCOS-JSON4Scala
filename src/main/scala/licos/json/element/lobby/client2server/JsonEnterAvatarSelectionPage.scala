package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.lobby.LobbyValidation

final case class JsonEnterAvatarSelectionPage(`type`: String, lobby: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonEnterAvatarSelectionPage.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(lobby: String) = {
    this(JsonEnterAvatarSelectionPage.`type`, lobby)
  }

}

object JsonEnterAvatarSelectionPage {

  val `type`: String = "enterAvatarSelectionPage"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonEnterAvatarSelectionPage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "lobby").read[String](LobbyValidation.lobby)
  )(JsonEnterAvatarSelectionPage.apply _)

  implicit val jsonWrites: OWrites[JsonEnterAvatarSelectionPage] = Json.writes[JsonEnterAvatarSelectionPage]

}
