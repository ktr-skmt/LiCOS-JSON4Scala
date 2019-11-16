package licos.json.element.village

import licos.bson.element.village.{BsonBase, BsonStar, BsonStarInfo}
import licos.bson.element.village.character.BsonRoleCharacter
import licos.json.element.Element
import licos.json.element.village.character.JsonRoleCharacter
import licos.json.validation.village.{AvatarValidation, BaseValidation, StarValidation}
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

final case class JsonStar(base: JsonBase, sub: JsonSubStar) extends JsonElement with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(base: JsonBase, myCharacter: JsonRoleCharacter, star: JsonStarInfo) = {
    this(base, JsonSubStar(myCharacter, star))
  }

  override def toBson: BsonStar = {
    new BsonStar(
      new ObjectId(),
      base.toBson:            BsonBase,
      sub.myCharacter.toBson: BsonRoleCharacter,
      sub.star.toBson:        BsonStarInfo
    )
  }
}

object JsonStar {

  implicit val jsonFormat: Format[JsonStar] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubStar]
  )(JsonStar.apply, unlift(JsonStar.unapply))
}

final case class JsonSubStar(myCharacter: JsonRoleCharacter, star: JsonStarInfo)

object JsonSubStar {
  implicit val jsonFormat: OFormat[JsonSubStar] = Json.format[JsonSubStar]
}

final case class JsonStarInfo(
    `@context`:      String,
    `@id`:           String,
    token:           String,
    serverTimestamp: String,
    clientTimestamp: String,
    isMarked:        Boolean
) extends JsonElement {

  override def toBson: BsonStarInfo = {
    new BsonStarInfo(
      new ObjectId(),
      `@context`:      String,
      `@id`:           String,
      token:           String,
      serverTimestamp: String,
      clientTimestamp: String,
      isMarked:        Boolean
    )
  }
}

object JsonStarInfo {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonStarInfo] = (
    (JsPath \ "@context").read[String](StarValidation.`@context`) and
      (JsPath \ "@id").read[String](StarValidation.`@id`) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "serverTimestamp").read[String](BaseValidation.serverTimestamp) and
      (JsPath \ "clientTimestamp").read[String](BaseValidation.clientTimestamp) and
      (JsPath \ "isMarked").read[Boolean]
  )(JsonStarInfo.apply _)

  implicit val jsonFormat: OFormat[JsonStarInfo] = Json.format[JsonStarInfo]
}
