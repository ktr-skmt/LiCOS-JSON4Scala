package licos.json.element.village.client2server

import licos.json.element.Element
import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.element.village.role.JsonSimpleRole
import licos.json.element.village.{JsonAvatar, JsonBase, JsonElement}
import licos.json.validation.village.BoardValidation
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath}

final case class JsonOnymousAudienceBoard private (base: JsonBase, sub: JsonSubOnymousAudienceBoard)
    extends JsonElement
    with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      base:       JsonBase,
      avatar:     JsonAvatar,
      character:  JsonSimpleCharacter,
      role:       JsonSimpleRole,
      prediction: String
  ) = {
    this(
      base,
      JsonSubOnymousAudienceBoard(
        avatar:     JsonAvatar,
        character:  JsonSimpleCharacter,
        role:       JsonSimpleRole,
        prediction: String
      )
    )
  }

  def avatar:     JsonAvatar          = sub.avatar
  def character:  JsonSimpleCharacter = sub.character
  def role:       JsonSimpleRole      = sub.role
  def prediction: String              = sub.prediction
}

object JsonOnymousAudienceBoard {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: Format[JsonOnymousAudienceBoard] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubOnymousAudienceBoard]
  )(JsonOnymousAudienceBoard.apply, unlift(JsonOnymousAudienceBoard.unapply))
}

final case class JsonSubOnymousAudienceBoard(
    avatar:     JsonAvatar,
    character:  JsonSimpleCharacter,
    role:       JsonSimpleRole,
    prediction: String
)

object JsonSubOnymousAudienceBoard {

  import play.api.libs.functional.syntax._
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonSubOnymousAudienceBoard] = (
    (JsPath \ "avatar").read[JsonAvatar] and
      (JsPath \ "character").read[JsonSimpleCharacter] and
      (JsPath \ "role").read[JsonSimpleRole] and
      (JsPath \ "prediction").read[String](BoardValidation.prediction)
  )(JsonSubOnymousAudienceBoard.apply _)

  implicit val jsonWrites: OWrites[JsonSubOnymousAudienceBoard] = Json.writes[JsonSubOnymousAudienceBoard]
}
