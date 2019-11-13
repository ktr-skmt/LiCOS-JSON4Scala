package licos.json.element.lobby

import licos.json.validation.village.AvatarValidation

final case class JsonKickOutPlayer(`type`: String, token: String, players: Seq[JsonPlayerTokenInKickOutPlayer])
    extends TypeSystem(`type`) {
  override protected def validType: String = JsonKickOutPlayer.`type`
}

object JsonKickOutPlayer {

  val `type`: String = "kickOutPlayer"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonKickOutPlayer] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "players").read[Seq[JsonPlayerTokenInKickOutPlayer]]
  )(JsonKickOutPlayer.apply _)

  implicit val jsonWrites: OWrites[JsonKickOutPlayer] = Json.writes[JsonKickOutPlayer]

}

final case class JsonPlayerTokenInKickOutPlayer(token: String)

object JsonPlayerTokenInKickOutPlayer {

  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonPlayerTokenInKickOutPlayer] =
    (JsPath \ "token").read[String](AvatarValidation.token).map(JsonPlayerTokenInKickOutPlayer.apply)

  implicit val jsonWrites: OWrites[JsonPlayerTokenInKickOutPlayer] = Json.writes[JsonPlayerTokenInKickOutPlayer]
}
