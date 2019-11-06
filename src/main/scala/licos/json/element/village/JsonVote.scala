package licos.json.element.village

import licos.bson.element.village.{BsonBase, BsonVote}
import licos.bson.element.village.character.{BsonRoleCharacter, BsonSimpleCharacter}
import licos.json.element.village.character.{JsonRoleCharacter, JsonSimpleCharacter}
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonVote private (base: JsonBase, sub: JsonSubVote) extends JsonElement {
  def this(base: JsonBase, character: JsonSimpleCharacter, myCharacter: JsonRoleCharacter) = this(
    base: JsonBase,
    JsonSubVote(
      character:   JsonSimpleCharacter,
      myCharacter: JsonRoleCharacter
    )
  )
  def character:   JsonSimpleCharacter = sub.character
  def myCharacter: JsonRoleCharacter   = sub.myCharacter
  override def toBson: BsonVote = {
    new BsonVote(
      new ObjectId(),
      base.toBson:        BsonBase,
      character.toBson:   BsonSimpleCharacter,
      myCharacter.toBson: BsonRoleCharacter
    )
  }
}

object JsonVote {
  def apply(base: JsonBase, character: JsonSimpleCharacter, myCharacter: JsonRoleCharacter): JsonVote = new JsonVote(
    base:        JsonBase,
    character:   JsonSimpleCharacter,
    myCharacter: JsonRoleCharacter
  )

  implicit val jsonFormat: Format[JsonVote] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubVote]
  )(JsonVote.apply, unlift(JsonVote.unapply))
}

case class JsonSubVote(character: JsonSimpleCharacter, myCharacter: JsonRoleCharacter)

object JsonSubVote {
  implicit val jsonFormat: OFormat[JsonSubVote] = Json.format[JsonSubVote]
}
