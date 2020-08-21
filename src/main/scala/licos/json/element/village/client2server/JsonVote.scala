package licos.json.element.village.client2server

import licos.json.element.Element
import licos.json.element.village.character.{JsonRoleCharacter, JsonSimpleCharacter}
import licos.json.element.village.{JsonBase, JsonElement}
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

final case class JsonVote private (base: JsonBase, sub: JsonSubVote) extends JsonElement with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(base: JsonBase, myCharacter: JsonRoleCharacter, character: JsonSimpleCharacter) = this(
    base: JsonBase,
    JsonSubVote(
      myCharacter: JsonRoleCharacter,
      character:   JsonSimpleCharacter
    )
  )
  def myCharacter: JsonRoleCharacter   = sub.myCharacter
  def character:   JsonSimpleCharacter = sub.character

}

object JsonVote {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: Format[JsonVote] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubVote]
  )(JsonVote.apply, unlift(JsonVote.unapply))
}

final case class JsonSubVote(myCharacter: JsonRoleCharacter, character: JsonSimpleCharacter)

object JsonSubVote {
  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: OFormat[JsonSubVote] = Json.format[JsonSubVote]
}
