package licos.json.element.village

import licos.bson.element.village.{BsonBase, BsonError, BsonName}
import licos.json.element.Element
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonError private (base: JsonBase, sub: JsonSubError) extends JsonElement with Element {
  def this(base: JsonBase, content: JsonName, severity: String, source: String, isFromServer: Boolean) = {
    this(
      base: JsonBase,
      JsonSubError(
        content:      JsonName,
        severity:     String,
        source:       String,
        isFromServer: Boolean
      )
    )
  }

  def content:      JsonName = sub.content
  def severity:     String   = sub.severity
  def source:       String   = sub.source
  def isFromServer: Boolean  = sub.isFromServer

  override def toBson: BsonError = {
    new BsonError(
      new ObjectId(),
      base.toBson:    BsonBase,
      content.toBson: BsonName,
      severity:       String,
      source:         String,
      isFromServer:   Boolean
    )
  }
}

object JsonError {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def apply(base: JsonBase, content: JsonName, severity: String, source: String, isFromServer: Boolean): JsonError = {
    new JsonError(
      base:         JsonBase,
      content:      JsonName,
      severity:     String,
      source:       String,
      isFromServer: Boolean
    )
  }

  implicit val jsonFormat: Format[JsonError] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubError]
  )(JsonError.apply, unlift(JsonError.unapply))
}

final case class JsonSubError(content: JsonName, severity: String, source: String, isFromServer: Boolean)

object JsonSubError {
  implicit val jsonFormat: OFormat[JsonSubError] = Json.format[JsonSubError]
}
