package licos.json.village

import licos.bson.village.{BsonBase, BsonError, BsonName}
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonError private (base: JsonBase,
                              sub: JsonSubError) extends JsonElement {
  def this(base: JsonBase,
           content: JsonName,
           severity: String,
           source: String) = {
    this(
      base: JsonBase,
      JsonSubError(
        content: JsonName,
        severity: String,
        source: String
      )
    )
  }

  def content: JsonName = sub.content
  def severity: String = sub.severity
  def source: String = sub.source

  override def toBson: BsonError = {
    new BsonError(
      new ObjectId(),
      base.toBson: BsonBase,
      content.toBson: BsonName,
      severity: String,
      source: String
    )
  }
}

object JsonError {
  def apply(base: JsonBase,
            content: JsonName,
            severity: String,
            source: String): JsonError = {
    new JsonError(
      base: JsonBase,
      content: JsonName,
      severity: String,
      source: String
    )
  }

  implicit val jsonFormat: Format[JsonError] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubError]
    )(JsonError.apply, unlift(JsonError.unapply))
}

case class JsonSubError(content: JsonName,
                        severity: String,
                        source: String)

object JsonSubError {
  implicit val jsonFormat: OFormat[JsonSubError] = Json.format[JsonSubError]
}