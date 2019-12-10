package licos.json.element.village

import licos.json.validation.village.ChatValidation

final case class JsonChatText(`@value`: String, `@language`: String) extends JsonElement

object JsonChatText {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonChatText] = (
    (JsPath \ "@value").read[String](ChatValidation.text.`@value`) and
      (JsPath \ "@language").read[String](ChatValidation.text.`@language`)
  )(JsonChatText.apply _)

  implicit val jsonWrites: OWrites[JsonChatText] = Json.writes[JsonChatText]
}
