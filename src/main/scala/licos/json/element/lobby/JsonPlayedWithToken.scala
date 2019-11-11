package licos.json.element.lobby

import licos.json.element.Element
import licos.json.validation.village.AvatarValidation

final case class JsonPlayedWithToken(to: String, json: JsonPlayed) extends Element

object JsonPlayedWithToken {

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonPlayedWithToken] = (
    (JsPath \ "to").read[String](AvatarValidation.token) and
      (JsPath \ "json").read[JsonPlayed]
  )(JsonPlayedWithToken.apply _)

  implicit val jsonWrites: OWrites[JsonPlayedWithToken] = Json.writes[JsonPlayedWithToken]
}
