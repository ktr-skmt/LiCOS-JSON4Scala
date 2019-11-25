package licos.json.element.lobby.server2server

import licos.json.element.Element
import licos.json.element.lobby.server2client.JsonPlayed
import licos.json.validation.village.AvatarValidation

final case class JsonPlayedWithToken(to: String, json: JsonPlayed) extends Element

object JsonPlayedWithToken {

  import play.api.libs.functional.syntax._
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonPlayedWithToken] = (
    (JsPath \ "to").read[String](AvatarValidation.token) and
      (JsPath \ "json").read[JsonPlayed]
  )(JsonPlayedWithToken.apply _)

  implicit val jsonWrites: OWrites[JsonPlayedWithToken] = Json.writes[JsonPlayedWithToken]
}
