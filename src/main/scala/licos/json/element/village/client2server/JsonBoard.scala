package licos.json.element.village.client2server

import licos.bson.element.village.character.{BsonRoleCharacter, BsonSimpleCharacter}
import licos.bson.element.village.role.BsonSimpleRole
import licos.bson.element.village.{BsonBase, BsonBoard}
import licos.json.element.Element
import licos.json.element.village.character.{JsonRoleCharacter, JsonSimpleCharacter}
import licos.json.element.village.role.JsonSimpleRole
import licos.json.element.village.{JsonBase, JsonElement}
import licos.json.validation.village.BoardValidation
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath}

final case class JsonBoard private (base: JsonBase, sub: JsonSubBoard) extends JsonElement with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      base:        JsonBase,
      myCharacter: JsonRoleCharacter,
      character:   JsonSimpleCharacter,
      role:        JsonSimpleRole,
      prediction:  String
  ) = {
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

  implicit val jsonFormat: Format[JsonBoard] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubBoard]
  )(JsonBoard.apply, unlift(JsonBoard.unapply))

}

final case class JsonSubBoard(
    myCharacter: JsonRoleCharacter,
    character:   JsonSimpleCharacter,
    role:        JsonSimpleRole,
    prediction:  String
)

object JsonSubBoard {

  import play.api.libs.functional.syntax._
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonSubBoard] = (
    (JsPath \ "myCharacter").read[JsonRoleCharacter] and
      (JsPath \ "character").read[JsonSimpleCharacter] and
      (JsPath \ "role").read[JsonSimpleRole] and
      (JsPath \ "prediction").read[String](BoardValidation.prediction)
  )(JsonSubBoard.apply _)

  implicit val jsonWrites: OWrites[JsonSubBoard] = Json.writes[JsonSubBoard]
}
