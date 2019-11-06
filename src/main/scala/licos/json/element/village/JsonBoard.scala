package licos.json.element.village

import licos.bson.element.village.role.BsonSimpleRole
import licos.bson.element.village.{BsonBase, BsonBoard}
import licos.bson.element.village.character.{BsonRoleCharacter, BsonSimpleCharacter}
import licos.json.element.village.character.{JsonRoleCharacter, JsonSimpleCharacter}
import licos.json.element.village.role.JsonSimpleRole
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonBoard private (base: JsonBase, sub: JsonSubBoard) extends JsonElement {
  def this(base:        JsonBase,
           myCharacter: JsonRoleCharacter,
           character:   JsonSimpleCharacter,
           role:        JsonSimpleRole,
           prediction:  String) = {
    this(
      base,
      JsonSubBoard(
        myCharacter: JsonRoleCharacter,
        character:   JsonSimpleCharacter,
        role:        JsonSimpleRole,
        prediction:  String
      )
    )
  }

  def myCharacter: JsonRoleCharacter   = sub.myCharacter
  def character:   JsonSimpleCharacter = sub.character
  def role:        JsonSimpleRole      = sub.role
  def prediction:  String              = sub.prediction

  override def toBson: BsonBoard = {
    new BsonBoard(
      new ObjectId(),
      base.toBson:        BsonBase,
      myCharacter.toBson: BsonRoleCharacter,
      character.toBson:   BsonSimpleCharacter,
      role.toBson:        BsonSimpleRole,
      prediction:         String
    )
  }
}

object JsonBoard {
  def apply(base:        JsonBase,
            myCharacter: JsonRoleCharacter,
            character:   JsonSimpleCharacter,
            role:        JsonSimpleRole,
            prediction:  String): JsonBoard = {
    new JsonBoard(
      base:        JsonBase,
      myCharacter: JsonRoleCharacter,
      character:   JsonSimpleCharacter,
      role:        JsonSimpleRole,
      prediction:  String
    )
  }

  implicit val jsonFormat: Format[JsonBoard] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubBoard]
  )(JsonBoard.apply, unlift(JsonBoard.unapply))
}

case class JsonSubBoard(myCharacter: JsonRoleCharacter,
                        character:   JsonSimpleCharacter,
                        role:        JsonSimpleRole,
                        prediction:  String)

object JsonSubBoard {
  implicit val jsonFormat: OFormat[JsonSubBoard] = Json.format[JsonSubBoard]
}
