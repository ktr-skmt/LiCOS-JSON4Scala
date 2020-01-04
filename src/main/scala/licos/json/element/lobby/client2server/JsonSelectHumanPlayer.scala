package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonSelectHumanPlayer(`type`: String, token: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonSelectHumanPlayer.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String) = {
    this(JsonSelectHumanPlayer.`type`, token)
  }

}

object JsonSelectHumanPlayer {

  val `type`: String = "selectHumanPlayer"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonSelectHumanPlayer] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token)
  )(JsonSelectHumanPlayer.apply _)

  implicit val jsonWrites: OWrites[JsonSelectHumanPlayer] = Json.writes[JsonSelectHumanPlayer]
}
