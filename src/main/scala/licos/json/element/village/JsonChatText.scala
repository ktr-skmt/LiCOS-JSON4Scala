package licos.json.element.village

import licos.bson.element.village.BsonChatText
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

final case class JsonChatText(`@value`: String, `@language`: String) extends JsonElement {
  override def toBson: BsonChatText = {
    new BsonChatText(
      new ObjectId(),
      `@value`:    String,
      `@language`: String
    )
  }
}

object JsonChatText {
  implicit val jsonFormat: OFormat[JsonChatText] = Json.format[JsonChatText]
}
