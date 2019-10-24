package licos.json.element.village

import licos.bson.element.village.character.BsonSimpleCharacter
import licos.bson.element.village.role.BsonSimpleRole
import licos.bson.element.village.{BsonAvatar, BsonBase, BsonOnymousAudienceBoard}
import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.element.village.role.JsonSimpleRole
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonOnymousAudienceBoard private (base: JsonBase,
                                             sub: JsonSubOnymousAudienceBoard) extends JsonElement {
  def this(base: JsonBase,
           avatar: JsonAvatar,
           character: JsonSimpleCharacter,
           role: JsonSimpleRole,
           prediction: String) = {
    this(
      base,
      JsonSubOnymousAudienceBoard(
        avatar: JsonAvatar,
        character: JsonSimpleCharacter,
        role: JsonSimpleRole,
        prediction: String
      )
    )
  }

  def avatar: JsonAvatar = sub.avatar
  def character: JsonSimpleCharacter = sub.character
  def role: JsonSimpleRole = sub.role
  def prediction: String = sub.prediction

  override def toBson: BsonOnymousAudienceBoard = {
    new BsonOnymousAudienceBoard(
      new ObjectId(),
      base.toBson: BsonBase,
      avatar.toBson: BsonAvatar,
      character.toBson: BsonSimpleCharacter,
      role.toBson: BsonSimpleRole,
      prediction: String
    )
  }
}

object JsonOnymousAudienceBoard {
  def apply(base: JsonBase,
            avatar: JsonAvatar,
            character: JsonSimpleCharacter,
            role: JsonSimpleRole,
            prediction: String): JsonOnymousAudienceBoard = {
    new JsonOnymousAudienceBoard(
      base: JsonBase,
      avatar: JsonAvatar,
      character: JsonSimpleCharacter,
      role: JsonSimpleRole,
      prediction: String
    )
  }

  implicit val jsonFormat: Format[JsonOnymousAudienceBoard] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubOnymousAudienceBoard]
    )(JsonOnymousAudienceBoard.apply, unlift(JsonOnymousAudienceBoard.unapply))
}

case class JsonSubOnymousAudienceBoard(avatar: JsonAvatar,
                                       character: JsonSimpleCharacter,
                                       role: JsonSimpleRole,
                                       prediction: String)

object JsonSubOnymousAudienceBoard {
  implicit val jsonFormat: OFormat[JsonSubOnymousAudienceBoard] = Json.format[JsonSubOnymousAudienceBoard]
}